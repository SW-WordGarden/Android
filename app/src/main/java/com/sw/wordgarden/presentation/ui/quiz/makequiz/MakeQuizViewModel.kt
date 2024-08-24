package com.sw.wordgarden.presentation.ui.quiz.makequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.quiz.SqCreatedInfoEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.usecase.quiz.sq.CreateNewSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.GetUserSqUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.Constants
import com.sw.wordgarden.presentation.mapper.ModelMapper.createEmptySqresultEntity
import com.sw.wordgarden.presentation.mapper.ModelMapper.toQuizModel
import com.sw.wordgarden.presentation.mapper.ModelMapper.toSqQuestionAnswerEntity
import com.sw.wordgarden.presentation.model.QAModel
import com.sw.wordgarden.presentation.model.QuizModel
import com.sw.wordgarden.presentation.shared.IsLoadingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MakeQuizViewModel @Inject constructor(
    private val getUserSqUseCase: GetUserSqUseCase,
    private val createNewSqUseCase: CreateNewSqUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _getSqEvent = MutableSharedFlow<DefaultEvent>()
    val getSqEvent: SharedFlow<DefaultEvent> = _getSqEvent.asSharedFlow()

    private val _getSq = MutableStateFlow<QuizModel?>(null)
    val getSq: StateFlow<QuizModel?> = _getSq.asStateFlow()

    private val _createNewSqEvent = MutableSharedFlow<DefaultEvent>()
    val createNewSqEvent: SharedFlow<DefaultEvent> = _createNewSqEvent.asSharedFlow()

    private val _createdSqInfo = MutableSharedFlow<SqCreatedInfoEntity?>()
    val createdSqInfo: SharedFlow<SqCreatedInfoEntity?> = _createdSqInfo.asSharedFlow()

    fun getUserSq(sqId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                val response = getUserSqUseCase(null, sqId)
                if (response != null) {
                    val quizModel = response.toQuizModel()
                    _getSq.update { quizModel }
                }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _getSqEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_fail_load_quiz_list))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _getSqEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun createNewSq(qaModelList: List<QAModel>, title: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                val sqEntity = SqEntity(
                    uid = "",
                    quizTitle = title,
                    sqId = "",
                    questionsAndAnswers = qaModelList.toSqQuestionAnswerEntity(),
                    sqresults = createEmptySqresultEntity()
                )

                val response = createNewSqUseCase.invoke(sqEntity)
                if (response != null) {
                    _createdSqInfo.emit(response)
                }
            }.onFailure { throwable ->
                _uiState.update { it.copy(isLoading = false) }

                when (throwable) {
                    is HttpException -> {
                        val errorResponse = throwable.response()?.errorBody()?.string()
                        if (throwable.code() == 403 && errorResponse == Constants.DUPLICATION_QUIZ_TITLE) {
                            _createNewSqEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_unknown_error))
                        } else {
                            _createNewSqEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_fail_make_quiz))
                        }
                    }
                }
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _createNewSqEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
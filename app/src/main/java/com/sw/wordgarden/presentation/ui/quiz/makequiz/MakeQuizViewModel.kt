package com.sw.wordgarden.presentation.ui.quiz.makequiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.usecase.quiz.sq.CreateNewSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSqUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.Constants
import com.sw.wordgarden.presentation.mapper.ModelMapper.createEmptySqresultEntity
import com.sw.wordgarden.presentation.mapper.ModelMapper.toListQAModel
import com.sw.wordgarden.presentation.mapper.ModelMapper.toSqQuestionAnswerEntity
import com.sw.wordgarden.presentation.model.QAModel
import com.sw.wordgarden.presentation.model.QuizModel
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
    private val getSqUseCase: GetSqUseCase,
    private val createNewSqUseCase: CreateNewSqUseCase
) : ViewModel() {

    private val _getSqEvent = MutableSharedFlow<DefaultEvent>()
    val getSqEvent: SharedFlow<DefaultEvent> = _getSqEvent.asSharedFlow()

    private val _getSq = MutableStateFlow<QuizModel?>(null)
    val getSq: StateFlow<QuizModel?> = _getSq.asStateFlow()

    private val _insertQuizEvent = MutableSharedFlow<DefaultEvent>()
    val insertQuizEvent: SharedFlow<DefaultEvent> = _insertQuizEvent.asSharedFlow()

    fun getQuiz(sqId: String, title: String) {
        viewModelScope.launch {
            runCatching {
                val response = getSqUseCase(sqId)
                if (response != null) {
                    val qaModel = response.toListQAModel()
                    val quizModel = QuizModel(
                        sqId = sqId,
                        qTitle = title,
                        qaList = qaModel
                    )
                    _getSq.update { quizModel }
                }
            }.onFailure {
                _getSqEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_fail_load_quiz_list))
            }.onSuccess {
                _getSqEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun insertQuiz(qaModelList: List<QAModel>, title: String) {
        viewModelScope.launch {
            runCatching {
                val sqEntity = SqEntity(
                    uid = "",
                    quizTitle = title,
                    sqId = "",
                    questionsAndAnswers = qaModelList.toSqQuestionAnswerEntity(),
                    sqresults = createEmptySqresultEntity()
                )

                createNewSqUseCase.invoke(sqEntity)
            }.onFailure { throwable ->
                when (throwable) {
                    is HttpException -> {
                        val errorResponse = throwable.response()?.errorBody()?.string()
                        if (throwable.code() == 403 && errorResponse == Constants.DUPLICATION_QUIZ_TITLE) {
                            _insertQuizEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_unknown_error))
                        } else {
                            _insertQuizEvent.emit(DefaultEvent.Failure(R.string.make_quiz_msg_fail_make_quiz))
                        }
                    }
                }
            }.onSuccess {
                _insertQuizEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
package com.sw.wordgarden.presentation.ui.quiz.startquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.quiz.sq.GetUserSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetGeneratedWqUseCase
import com.sw.wordgarden.presentation.mapper.ModelMapper.toModel
import com.sw.wordgarden.presentation.event.DefaultEvent
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
import javax.inject.Inject

@HiltViewModel
class StartQuizViewModel @Inject constructor(
    private val getUserSqUseCase: GetUserSqUseCase,
    private val getGeneratedWqUseCase: GetGeneratedWqUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _getQuizEvent = MutableSharedFlow<DefaultEvent>()
    val getQuizEvent: SharedFlow<DefaultEvent> = _getQuizEvent.asSharedFlow()

    private val _getQuiz = MutableStateFlow<QuizModel?>(null)
    val getQuiz: StateFlow<QuizModel?> = _getQuiz.asStateFlow()

    fun getQuizFromSq(creatorUid: String, sqId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                val response = getUserSqUseCase.invoke(creatorUid, sqId)
                val quizModel = response?.toModel()
                _getQuiz.update { quizModel }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _getQuizEvent.emit(DefaultEvent.Failure(R.string.start_quiz_msg_fail_get_quiz))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _getQuizEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun getQuizFromWq() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                val response = getGeneratedWqUseCase.invoke()
                val quizModel = response?.toModel()
                _getQuiz.update { quizModel }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _getQuizEvent.emit(DefaultEvent.Failure(R.string.start_quiz_msg_fail_get_quiz))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _getQuizEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
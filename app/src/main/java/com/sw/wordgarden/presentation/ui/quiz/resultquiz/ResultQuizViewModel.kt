package com.sw.wordgarden.presentation.ui.quiz.resultquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.quiz.sq.GetSolvedSqUseCase
import com.sw.wordgarden.domain.usecase.quiz.wq.GetSolvedWqUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.mapper.ModelMapper.toModel
import com.sw.wordgarden.presentation.model.QuizKey
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
class ResultQuizViewModel @Inject constructor(
    private val getSolvedSqUseCase: GetSolvedSqUseCase,
    private val getSolvedWqUseCase: GetSolvedWqUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _getResultEvent = MutableSharedFlow<DefaultEvent>()
    val getResultEvent: SharedFlow<DefaultEvent> = _getResultEvent.asSharedFlow()

    private val _getResult = MutableStateFlow<QuizModel?>(null)
    val getResult: StateFlow<QuizModel?> = _getResult.asStateFlow()

    fun getResult(quizKey: QuizKey) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                if (quizKey.isWq == true) {
                    val response = getSolvedWqUseCase.invoke(quizKey.qTitle ?: "")
                    _getResult.update { response?.toModel() }
                } else {
                    val response = getSolvedSqUseCase.invoke(quizKey.sqId ?: "")
                    _getResult.update { response?.toModel() }
                }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _getResultEvent.emit(DefaultEvent.Failure(R.string.result_quiz_msg_fail_get_result))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _getResultEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
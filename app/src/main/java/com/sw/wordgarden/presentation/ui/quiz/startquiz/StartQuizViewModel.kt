package com.sw.wordgarden.presentation.ui.quiz.startquiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.usecase.GetTodayQuizUseCase
import com.sw.wordgarden.domain.usecase.SaveDailyLimitUseCase
import com.sw.wordgarden.presentation.model.DefaultEvent
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
    private val getTodayQuizUseCase: GetTodayQuizUseCase,
    private val saveDailyLimitUseCase: SaveDailyLimitUseCase
) : ViewModel() {

    private val _getQuizEvent = MutableSharedFlow<DefaultEvent>()
    val getQuizEvent: SharedFlow<DefaultEvent> = _getQuizEvent.asSharedFlow()

    private val _getQuiz = MutableStateFlow<QuizListEntity?>(null)
    val getQuiz: StateFlow<QuizListEntity?> = _getQuiz.asStateFlow()

    private val _saveDailyLimitEvent = MutableSharedFlow<DefaultEvent>()
    val saveDailyLimitEvent: SharedFlow<DefaultEvent> = _saveDailyLimitEvent.asSharedFlow()

    init {
        getQuiz()
    }

    private fun getQuiz() {
        viewModelScope.launch {
            runCatching {
                _getQuiz.update { getTodayQuizUseCase.invoke() }
            }.onFailure {
                _getQuizEvent.emit(DefaultEvent.Failure(R.string.start_quiz_msg_fail_get_quiz))
            }.onSuccess {
                _getQuizEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun saveDailyLimit(count: Int) {
        viewModelScope.launch {
            runCatching {
                saveDailyLimitUseCase.invoke(count)
            }.onFailure {
                _saveDailyLimitEvent.emit(DefaultEvent.Failure(R.string.start_quiz_msg_fail_save_daily_limit))
            }.onSuccess {
                _saveDailyLimitEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
package com.sw.wordgarden.presentation.ui.quiz.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.datastore.GetDailyLimitUseCase
import com.sw.wordgarden.domain.usecase.datastore.SaveDailyLimitUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
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
class QuizViewModel @Inject constructor(
    private val getDailyLimitUseCase: GetDailyLimitUseCase,
    private val saveDailyLimitUseCase: SaveDailyLimitUseCase
) : ViewModel() {

    private val _getDailyLimitEvent = MutableSharedFlow<DefaultEvent>()
    val getDailyLimitEvent: SharedFlow<DefaultEvent> = _getDailyLimitEvent.asSharedFlow()

    private val _getDailyLimit = MutableStateFlow<Int?>(null)
    val getDailyLimit: StateFlow<Int?> = _getDailyLimit.asStateFlow()

    private val _saveDailyLimitEvent = MutableSharedFlow<DefaultEvent>()
    val saveDailyLimitEvent: SharedFlow<DefaultEvent> = _saveDailyLimitEvent.asSharedFlow()

    init {
        getDailyLimit()
    }

    private fun getDailyLimit() {
        viewModelScope.launch {
            runCatching {
                getDailyLimitUseCase.invoke()
            }.onFailure {
                _getDailyLimitEvent.emit(DefaultEvent.Failure(R.string.quiz_msg_fail_get_daily_limit))
            }.onSuccess { limit ->
                _getDailyLimit.update { limit }
                _getDailyLimitEvent.emit(DefaultEvent.Success)
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
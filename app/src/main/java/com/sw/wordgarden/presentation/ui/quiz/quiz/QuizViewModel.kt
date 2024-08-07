package com.sw.wordgarden.presentation.ui.quiz.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.GetDailyLimitUseCase
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
class QuizViewModel @Inject constructor(
    private val getDailyLimitUseCase: GetDailyLimitUseCase
) : ViewModel() {

    private val _getDailyLimitEvent = MutableSharedFlow<DefaultEvent>()
    val getDailyLimitEvent: SharedFlow<DefaultEvent> = _getDailyLimitEvent.asSharedFlow()

    private val _getDailyLimit = MutableStateFlow<Int?>(null)
    val getDailyLimit: StateFlow<Int?> = _getDailyLimit.asStateFlow()

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
}
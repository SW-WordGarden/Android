package com.sw.wordgarden.presentation.ui.alarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.AlarmEntity
import com.sw.wordgarden.domain.usecase.GetAlarmListUseCase
import com.sw.wordgarden.domain.usecase.GetQuizBySelfQuizIdUseCase
import com.sw.wordgarden.presentation.mapper.ModelMapper.toModel
import com.sw.wordgarden.presentation.model.DefaultEvent
import com.sw.wordgarden.presentation.model.SelfQuizModel
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
class AlarmViewModel @Inject constructor(
    private val getAlarmListUseCase: GetAlarmListUseCase,
    private val getQuizBySelfQuizIdUseCase: GetQuizBySelfQuizIdUseCase
) : ViewModel() {
    private val _getAlarmListEvent = MutableSharedFlow<DefaultEvent>()
    val getAlarmListEvent: SharedFlow<DefaultEvent> = _getAlarmListEvent.asSharedFlow()

    private val _getAlarmList = MutableStateFlow<List<AlarmEntity>?>(emptyList())
    val getAlarmList: StateFlow<List<AlarmEntity>?> = _getAlarmList.asStateFlow()

    private val _getQuizByQuizIdEvent = MutableSharedFlow<DefaultEvent>()
    val getQuizByQuizIdEvent: SharedFlow<DefaultEvent> = _getQuizByQuizIdEvent.asSharedFlow()

    private val _getQuizByQuizId = MutableStateFlow<SelfQuizModel?>(null)
    val getQuizByQuizId: StateFlow<SelfQuizModel?> = _getQuizByQuizId.asStateFlow()

    init {
        getAlarmList()
    }

    private fun getAlarmList() {
        viewModelScope.launch {
            runCatching {
                getAlarmListUseCase.invoke()
            }.onFailure {
                _getAlarmListEvent.emit(DefaultEvent.Failure(R.string.alarm_msg_fail_load_alarm))
            }.onSuccess { alarms ->
                _getAlarmList.update { alarms }
                _getAlarmListEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun getQuiz(quizId: String) {
        viewModelScope.launch {
            runCatching {
                val quiz = getQuizBySelfQuizIdUseCase.invoke(quizId)

                if (quiz != null) {
                    val quizModel = quiz.toModel()
                    _getQuizByQuizId.update { quizModel }
                }
            }.onFailure {
                _getQuizByQuizIdEvent.emit(DefaultEvent.Failure(R.string.alarm_msg_fail_get_quiz))
            }.onSuccess {
                _getQuizByQuizIdEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
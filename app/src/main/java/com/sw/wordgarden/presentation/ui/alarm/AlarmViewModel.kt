package com.sw.wordgarden.presentation.ui.alarm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity
import com.sw.wordgarden.domain.usecase.alarm.DeleteAlarmUseCase
import com.sw.wordgarden.domain.usecase.alarm.GetAlarmsUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
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
class AlarmViewModel @Inject constructor(
    private val getAlarmsUseCase: GetAlarmsUseCase,
    private val deleteAlarmUseCase: DeleteAlarmUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _getAlarmListEvent = MutableSharedFlow<DefaultEvent>()
    val getAlarmListEvent: SharedFlow<DefaultEvent> = _getAlarmListEvent.asSharedFlow()

    private val _getAlarmList = MutableStateFlow<List<AlarmEntity>?>(emptyList())
    val getAlarmList: StateFlow<List<AlarmEntity>?> = _getAlarmList.asStateFlow()

    private val _deleteAlarmEvent = MutableSharedFlow<DefaultEvent>()
    val deleteAlarmEvent: SharedFlow<DefaultEvent> = _deleteAlarmEvent.asSharedFlow()

    init {
        getAlarmList()
    }

    private fun getAlarmList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                getAlarmsUseCase.invoke()
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _getAlarmListEvent.emit(DefaultEvent.Failure(R.string.alarm_msg_fail_load_alarm))
            }.onSuccess { alarms ->
                _uiState.update { it.copy(isLoading = false) }
                _getAlarmList.update { alarms }
                _getAlarmListEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun deleteAlarm(alarmId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                deleteAlarmUseCase.invoke(alarmId)
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _deleteAlarmEvent.emit(DefaultEvent.Failure(R.string.alarm_msg_fail_delete_alarm))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _deleteAlarmEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
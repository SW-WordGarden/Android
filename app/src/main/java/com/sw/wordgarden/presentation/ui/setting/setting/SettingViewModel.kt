package com.sw.wordgarden.presentation.ui.setting.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.datastore.DeleteUidUseCase
import com.sw.wordgarden.domain.usecase.user.DeleteAccountUseCase
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
class SettingViewModel @Inject constructor(
    private val deleteUidUseCase: DeleteUidUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _deleteUidEvent = MutableSharedFlow<DefaultEvent>()
    val deleteUidEvent: SharedFlow<DefaultEvent> = _deleteUidEvent.asSharedFlow()

    private val _deleteAccountEvent = MutableSharedFlow<DefaultEvent>()
    val deleteAccountEvent: SharedFlow<DefaultEvent> = _deleteAccountEvent.asSharedFlow()

    fun deleteUidForLogout() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                deleteUidUseCase.invoke()
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _deleteUidEvent.emit(DefaultEvent.Failure(R.string.setting_msg_fail_logout))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _deleteUidEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                deleteAccountUseCase.invoke()
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _deleteAccountEvent.emit(DefaultEvent.Failure(R.string.setting_msg_fail_withdrawal))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _deleteAccountEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
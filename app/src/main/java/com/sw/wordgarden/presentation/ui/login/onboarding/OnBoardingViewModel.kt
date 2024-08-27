package com.sw.wordgarden.presentation.ui.login.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.domain.usecase.datastore.DeleteUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.SaveUidUseCase
import com.sw.wordgarden.domain.usecase.user.InsertUserUseCase
import com.sw.wordgarden.domain.usecase.user.UpdateUserImageUseCase
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
class OnBoardingViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val updateUserImageUseCase: UpdateUserImageUseCase,
    private val deleteUidUseCase: DeleteUidUseCase,
    private val saveUidUseCase: SaveUidUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _insertEvent = MutableSharedFlow<DefaultEvent>()
    val insertEvent: SharedFlow<DefaultEvent> = _insertEvent.asSharedFlow()

    private val _updateEvent = MutableSharedFlow<DefaultEvent>()
    val updateEvent: SharedFlow<DefaultEvent> = _updateEvent.asSharedFlow()

    private val _deleteUidEvent = MutableSharedFlow<DefaultEvent>()
    val deleteUidEvent: SharedFlow<DefaultEvent> = _deleteUidEvent.asSharedFlow()

    private val _saveUidEvent = MutableSharedFlow<DefaultEvent>()
    val saveUidEvent: SharedFlow<DefaultEvent> = _saveUidEvent.asSharedFlow()

    fun signUp(loginRequestEntity: LoginRequestEntity) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                insertUserUseCase.invoke(loginRequestEntity)
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _insertEvent.emit(DefaultEvent.Failure(R.string.onboarding_msg_fail_signup))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _insertEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun updateImage(image: String) { //TODO:회원 가입 시 일괄 처리하도록 서버 쪽에 요청 필요
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                updateUserImageUseCase.invoke(image)
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _updateEvent.emit(DefaultEvent.Failure(R.string.onboarding_msg_fail_update_image))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _updateEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun deleteUidForStartingSignUp() {
        viewModelScope.launch {
            runCatching {
                deleteUidUseCase.invoke()
            }.onFailure {
                _deleteUidEvent.emit(DefaultEvent.Failure(R.string.login_msg_can_not_delete_uid))
            }.onSuccess {
                _deleteUidEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun saveUid(uid: String) {
        viewModelScope.launch {
            runCatching {
                saveUidUseCase.invoke(uid)
            }.onFailure {
                _saveUidEvent.emit(DefaultEvent.Failure(R.string.login_msg_can_not_save_uid))
            }.onSuccess {
                _saveUidEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
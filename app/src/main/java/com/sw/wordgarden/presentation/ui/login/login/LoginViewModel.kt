package com.sw.wordgarden.presentation.ui.login.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.datastore.DeleteUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.GetUidUseCase
import com.sw.wordgarden.domain.usecase.datastore.SaveUidUseCase
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForLoginUseCase
import com.sw.wordgarden.domain.usecase.user.UpdateFcmTokenUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.util.Constants
import com.sw.wordgarden.presentation.event.UserCheckEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUidUseCase: GetUidUseCase,
    private val getUserInfoForLoginUseCase: GetUserInfoForLoginUseCase,
    private val deleteUidUseCase: DeleteUidUseCase,
    private val saveUidUserCase: SaveUidUseCase,
    private val updateFcmTokenUseCase: UpdateFcmTokenUseCase,
) : ViewModel() {

    private val _getUidEvent = MutableSharedFlow<DefaultEvent>()
    val getUidEvent: SharedFlow<DefaultEvent> = _getUidEvent.asSharedFlow()

    private val _getUid = MutableStateFlow<String?>(null)
    val getUid: MutableStateFlow<String?> = _getUid

    private val _checkUserEvent = MutableSharedFlow<UserCheckEvent>()
    val checkUserEvent: SharedFlow<UserCheckEvent> = _checkUserEvent.asSharedFlow()

    private val _deleteUidEvent = MutableSharedFlow<DefaultEvent>()
    val deleteUidEvent: SharedFlow<DefaultEvent> = _deleteUidEvent.asSharedFlow()

    private val _saveUidEvent = MutableSharedFlow<DefaultEvent>()
    val saveUidEvent: SharedFlow<DefaultEvent> = _saveUidEvent.asSharedFlow()

    private val _updateTokenEvent = MutableSharedFlow<DefaultEvent>()
    val updateTokenEvent: SharedFlow<DefaultEvent> = _updateTokenEvent.asSharedFlow()

    init {
        getUidData()
    }

    private fun getUidData() {
        viewModelScope.launch {
            runCatching {
                val uid = getUidUseCase.invoke()

                if (uid != null) {
                    _getUid.update { uid }
                } else {
                    _getUid.update { null }
                }
            }.onFailure {
                _getUidEvent.emit(DefaultEvent.Failure(R.string.login_msg_fail_auto_login))
            }.onSuccess {
                _getUidEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun checkUserInfo(uid: String) {
        viewModelScope.launch {
            runCatching {
                val result = getUserInfoForLoginUseCase.invoke(uid)
                if (result != null) { //앱 유저 확인 성공
                    _checkUserEvent.emit(UserCheckEvent.Success)
                } else {
                    _checkUserEvent.emit(UserCheckEvent.NotFound)
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is HttpException -> {
                        val errorResponse = throwable.response()?.errorBody()?.string()
                        if (throwable.code() == 404 && errorResponse == Constants.USER_NOT_FOUND) {
                            _checkUserEvent.emit(UserCheckEvent.NotFound)
                        } else {
                            _checkUserEvent.emit(UserCheckEvent.Failure(R.string.login_msg_fail_check))
                        }
                    }
                    else -> {
                        _checkUserEvent.emit(UserCheckEvent.Failure(R.string.login_msg_fail_check))
                    }
                }
            }
        }
    }

    fun deleteUidForStartingLogin() {
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
                saveUidUserCase.invoke(uid)
            }.onFailure {
                _saveUidEvent.emit(DefaultEvent.Failure(R.string.login_msg_can_not_save_uid))
            }.onSuccess {
                _saveUidEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun updateToken(token: String) {
        viewModelScope.launch {
            runCatching {
                updateFcmTokenUseCase.invoke(token)
            }.onFailure {
                _updateTokenEvent.emit(DefaultEvent.Failure(R.string.login_msg_can_not_update_token))
            }.onSuccess {
                _updateTokenEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
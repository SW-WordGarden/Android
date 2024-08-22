package com.sw.wordgarden.presentation.ui.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.datastore.DeleteUidUseCase
import kotlinx.coroutines.launch
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForLoginUseCase
import com.sw.wordgarden.domain.usecase.datastore.SaveUidUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.event.UserCheckEvent
import com.sw.wordgarden.presentation.event.ErrorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserInfoForLoginUseCase: GetUserInfoForLoginUseCase,
    private val deleteUidUseCase: DeleteUidUseCase,
    private val saveUidUserCase: SaveUidUseCase,
) : ViewModel() {

    private val _checkUserEvent = MutableSharedFlow<UserCheckEvent>()
    val checkUserEvent: SharedFlow<UserCheckEvent> = _checkUserEvent.asSharedFlow()

    private val _deleteUidEvent = MutableSharedFlow<DefaultEvent>()
    val deleteUidEvent: SharedFlow<DefaultEvent> = _deleteUidEvent.asSharedFlow()

    private val _saveUidEvent = MutableSharedFlow<DefaultEvent>()
    val saveUidEvent: SharedFlow<DefaultEvent> = _saveUidEvent.asSharedFlow()

    fun checkUserInfo(uid: String) {
        viewModelScope.launch {
            runCatching {
                deleteUid()

                val result = getUserInfoForLoginUseCase.invoke(uid)
                if (result != null) { //앱 유저 확인 성공
                    _checkUserEvent.emit(UserCheckEvent.Success)
                } else {
                    _checkUserEvent.emit(UserCheckEvent.NotFound)
                }

                saveUid(uid)
            }.onFailure { throwable ->
                when (throwable) {
                    is HttpException -> {
                        val errorResponse = throwable.response()?.errorBody()?.string()
                        if (throwable.code() == 404 && errorResponse == ErrorMessage.USER_NOT_FOUND.name) {
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

    private fun deleteUid() {
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

    private fun saveUid(uid: String) {
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
}
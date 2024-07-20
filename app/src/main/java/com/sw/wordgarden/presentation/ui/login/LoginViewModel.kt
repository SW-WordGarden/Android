package com.sw.wordgarden.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.UserEntity
import kotlinx.coroutines.launch
import com.sw.wordgarden.domain.usecase.GetUserInfoUseCase
import com.sw.wordgarden.presentation.model.DefaultEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _checkEvent = MutableSharedFlow<DefaultEvent>()
    val checkEvent: SharedFlow<DefaultEvent> = _checkEvent.asSharedFlow()

    private val _checkUser = MutableSharedFlow<UserEntity>()
    val checkUser: SharedFlow<UserEntity> = _checkUser.asSharedFlow()

    fun checkUserInfo(uid: String) {
        viewModelScope.launch {
            try {
                val result = getUserInfoUseCase.invoke(uid)

                if (result != null) {
                    _checkEvent.emit(DefaultEvent.Success)
                    _checkUser.emit(result)
                } else {
                    _checkEvent.emit(DefaultEvent.Failure(R.string.login_msg_fail_check))
                }
            } catch (e: Exception) {
                _checkEvent.emit(DefaultEvent.Failure(R.string.login_msg_fail_check))
            }
        }
    }
}
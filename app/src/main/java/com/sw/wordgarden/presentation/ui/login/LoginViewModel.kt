package com.sw.wordgarden.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import kotlinx.coroutines.launch
import com.sw.wordgarden.domain.usecase.GetUserInfoUseCase
import com.sw.wordgarden.presentation.model.UserCheckEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _checkEvent = MutableSharedFlow<UserCheckEvent>()
    val checkEvent: SharedFlow<UserCheckEvent> = _checkEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            delay(1000)
            _isLoading.value = false
        }
    }

    fun checkUserInfo(uid: String) {

        viewModelScope.launch {
            runCatching {
                val result = getUserInfoUseCase.invoke(uid)
                if (result != null) {
                    _checkEvent.emit(UserCheckEvent.Success)
                } else {
                    _checkEvent.emit(UserCheckEvent.NotFound)
                }
            }.onFailure { throwable ->
                when (throwable) {
                    is HttpException -> {
                        val errorResponse = throwable.response()?.errorBody()?.string()
                        if (throwable.code() == 404 && errorResponse == "User not found") {
                            _checkEvent.emit(UserCheckEvent.NotFound)
                        } else {
                            _checkEvent.emit(UserCheckEvent.Failure(R.string.login_msg_fail_check))
                        }
                    }
                    else -> {
                        _checkEvent.emit(UserCheckEvent.Failure(R.string.login_msg_fail_check))
                    }
                }
            }
        }
    }
}
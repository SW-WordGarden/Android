package com.sw.wordgarden.presentation.ui.quiz.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.usecase.datastore.GetUidUseCase
import com.sw.wordgarden.domain.usecase.user.GetUserInfoUseCase
import com.sw.wordgarden.presentation.event.DefaultEvent
import com.sw.wordgarden.presentation.event.UserCheckEvent
import com.sw.wordgarden.presentation.shared.IsLoadingUiState
import com.sw.wordgarden.presentation.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getUidUseCase: GetUidUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : ViewModel() {

    private val _getUidEvent = MutableSharedFlow<DefaultEvent>()
    val getUidEvent: SharedFlow<DefaultEvent> = _getUidEvent.asSharedFlow()

    private val _getUid = MutableStateFlow<String?>(null)
    val getUid: MutableStateFlow<String?> = _getUid

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _checkUserEvent = MutableSharedFlow<UserCheckEvent>()
    val checkUserEvent: SharedFlow<UserCheckEvent> = _checkUserEvent.asSharedFlow()

    private val _getLimit = MutableStateFlow<Int?>(null)
    val getLimit: StateFlow<Int?> = _getLimit.asStateFlow()

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
                _getUidEvent.emit(DefaultEvent.Failure(R.string.login_msg_fail_check))
            }.onSuccess {
                _getUidEvent.emit(DefaultEvent.Success)
            }
        }
    }

    fun checkUserInfo(uid: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {

                val result = getUserInfoUseCase.invoke(uid)
                if (result != null) { //앱 유저 확인 성공
                    _getLimit.update { result.uParticipate }
                    _checkUserEvent.emit(UserCheckEvent.Success)
                    _uiState.update { it.copy(isLoading = false) }
                } else {
                    _checkUserEvent.emit(UserCheckEvent.NotFound)
                }
            }.onFailure { throwable ->
                _uiState.update { it.copy(isLoading = false) }

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
}
package com.sw.wordgarden.presentation.ui.mypage.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.user.UserInfoEntity
import com.sw.wordgarden.domain.usecase.user.GetUserInfoForMypage
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
class MyPageViewModel @Inject constructor(
    private val getUserInfoForMypage: GetUserInfoForMypage,
    private val updateUserImageUseCase: UpdateUserImageUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _getUserInfoEvent = MutableSharedFlow<DefaultEvent>()
    val getUserInfoEvent: SharedFlow<DefaultEvent> = _getUserInfoEvent.asSharedFlow()

    private val _getUserInfo = MutableStateFlow<UserInfoEntity?>(null)
    val getUserInfo: StateFlow<UserInfoEntity?> = _getUserInfo.asStateFlow()

    private val _updateUserImageEvent = MutableSharedFlow<DefaultEvent>()
    val updateUserImageEvent: SharedFlow<DefaultEvent> = _updateUserImageEvent.asSharedFlow()

    init {
        getUserInfoData()
    }

    fun getUserInfoData() =
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                val response = getUserInfoForMypage()

                if (response != null) {
                    _getUserInfo.update { response }
                } else {
                    _getUserInfo.update { null }
                }
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _getUserInfoEvent.emit(DefaultEvent.Failure(R.string.mypage_my_self_quiz_msg_fail_load_quiz_title_list))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _getUserInfoEvent.emit(DefaultEvent.Success)
            }
        }

    fun updateUserImage(image: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                updateUserImageUseCase.invoke(image)
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _updateUserImageEvent.emit(DefaultEvent.Failure(R.string.mypage_msg_fail_update_image))
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _updateUserImageEvent.emit(DefaultEvent.Success)
            }
        }
    }
}
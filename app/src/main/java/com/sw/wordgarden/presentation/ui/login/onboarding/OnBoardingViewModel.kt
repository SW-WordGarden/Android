package com.sw.wordgarden.presentation.ui.login.onboarding

import androidx.lifecycle.*
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.domain.usecase.user.InsertUserUseCase
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
) : ViewModel() {

    private val _uiState = MutableStateFlow(IsLoadingUiState.init())
    val uiState: StateFlow<IsLoadingUiState> = _uiState.asStateFlow()

    private val _insertEvent = MutableSharedFlow<DefaultEvent>()
    val insertEvent: SharedFlow<DefaultEvent> = _insertEvent.asSharedFlow()

    fun signUp(loginRequestEntity: LoginRequestEntity) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            runCatching {
                insertUserUseCase.invoke(loginRequestEntity)
            }.onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _insertEvent.emit(DefaultEvent.Success)
            }.onSuccess {
                _uiState.update { it.copy(isLoading = false) }
                _insertEvent.emit(DefaultEvent.Failure(R.string.onboarding_msg_fail_signup))
            }
        }
    }
}
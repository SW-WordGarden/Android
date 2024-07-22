package com.sw.wordgarden.presentation.ui.login

import androidx.lifecycle.*
import com.sw.wordgarden.R
import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.usecase.InsertUserUseCase
import com.sw.wordgarden.presentation.model.DefaultEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
) : ViewModel() {

    private val _insertEvent = MutableSharedFlow<DefaultEvent>()
    val insertEvent: SharedFlow<DefaultEvent> = _insertEvent.asSharedFlow()

    fun signUp(signUpEntity: SignUpEntity) {
        viewModelScope.launch {
            try {
                insertUserUseCase.invoke(signUpEntity)
                _insertEvent.emit(DefaultEvent.Success)
            } catch (e: Exception) {
                _insertEvent.emit(DefaultEvent.Failure(R.string.onboarding_msg_fail_signup))
            }
        }
    }
}
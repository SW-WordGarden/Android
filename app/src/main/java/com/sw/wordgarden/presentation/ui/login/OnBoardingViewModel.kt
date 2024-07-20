package com.sw.wordgarden.presentation.ui.login

import androidx.lifecycle.*
import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.usecase.InsertUserUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val insertUserUseCaseImpl: InsertUserUseCaseImpl,
) : ViewModel() {

    fun signUp(signUpEntity: SignUpEntity) {
        viewModelScope.launch {
            try {
                insertUserUseCaseImpl.invoke(signUpEntity)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
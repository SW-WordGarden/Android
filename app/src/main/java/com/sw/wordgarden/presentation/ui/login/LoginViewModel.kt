package com.sw.wordgarden.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.sw.wordgarden.domain.usecase.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    fun checkUserInfo(uid: String) {
        viewModelScope.launch {
            try {
                getUserInfoUseCase.invoke(uid)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
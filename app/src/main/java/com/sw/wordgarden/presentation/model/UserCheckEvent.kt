package com.sw.wordgarden.presentation.model

import androidx.annotation.StringRes

sealed interface UserCheckEvent {
    data object Success: UserCheckEvent
    data object NotFound: UserCheckEvent
    data class Failure(@StringRes val msg: Int): UserCheckEvent
}
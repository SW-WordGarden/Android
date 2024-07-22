package com.sw.wordgarden.presentation.model

import androidx.annotation.StringRes

sealed interface DefaultEvent {
    data object Success: DefaultEvent
    data class Failure(@StringRes val msg: Int): DefaultEvent
}
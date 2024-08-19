package com.sw.wordgarden.presentation.event

import androidx.annotation.StringRes

sealed interface DefaultEvent {
    data object Success: DefaultEvent
    data class Failure(@StringRes val msg: Int): DefaultEvent
}
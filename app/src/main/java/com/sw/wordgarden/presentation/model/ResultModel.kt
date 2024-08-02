package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultModel(
    val question: String,
    val correct: String,
    val enteredAnswer: String,
    val check: Boolean,
) : Parcelable
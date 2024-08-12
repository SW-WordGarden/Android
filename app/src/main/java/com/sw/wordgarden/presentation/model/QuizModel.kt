package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizModel(
    var question: String?,
    var answer: String?,
    val answerNumber: Int?,
) : Parcelable
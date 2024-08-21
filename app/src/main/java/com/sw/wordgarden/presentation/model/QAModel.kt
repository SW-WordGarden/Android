package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QAModel(
    var questionId: String?,
    var question: String?,
    var userAnswer: String?,
    var correctAnswer: String?,
    val correct: Boolean?,
) : Parcelable

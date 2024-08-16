package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionModel(
    var question: String?,
    var answer: String?,
    val questionNumber: Int?,
) : Parcelable
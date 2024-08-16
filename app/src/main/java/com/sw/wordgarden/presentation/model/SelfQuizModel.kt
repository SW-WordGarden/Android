package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelfQuizModel(
    val quizId: String?,
    val title: String?,
    val quiz: List<QuestionModel>?,
    val quizResult: List<QuestionResultModel>?,
) : Parcelable
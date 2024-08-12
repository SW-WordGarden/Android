package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizListModel(
    val title: String?,
    val quiz: List<QuizModel>?,
    val quizResult: List<QuizResultModel>?,
) : Parcelable
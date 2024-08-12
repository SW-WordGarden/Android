package com.sw.wordgarden.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizListEntity(
    val title: String?,
    val quiz: List<QuizEntity>?,
    val quizResult: List<QuizResultEntity>?,
) : Parcelable
package com.sw.wordgarden.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SelfQuizEntity(
    val quizId: String?,
    val title: String?,
    val quiz: List<QuestionEntity>?,
    val quizResult: List<QuestionResultEntity>?,
) : Parcelable
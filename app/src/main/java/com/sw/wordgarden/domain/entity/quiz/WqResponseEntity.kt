package com.sw.wordgarden.domain.entity.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WqResponseEntity(
    val wqId: String?,
    val wqQuestion: String?,
    val wqTitle: String?,
    val wordId: String?,
    val word: String?,
    val questionType: String?,
    val options: List<String>?,
    val userAnswer: String?,
    val correctAnswer: String?,
) : Parcelable
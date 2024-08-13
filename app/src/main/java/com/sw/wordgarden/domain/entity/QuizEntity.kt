package com.sw.wordgarden.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizEntity(
    val question: String?,
    val answer: String?,
    val questionNumber: Int?,
) : Parcelable
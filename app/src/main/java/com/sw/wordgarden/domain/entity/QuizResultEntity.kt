package com.sw.wordgarden.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class QuizResultEntity(
    val userAnswer: String?,
    val correct: Boolean?,
    val time: Timestamp?,
    val questionNumber: Int?,
) : Parcelable

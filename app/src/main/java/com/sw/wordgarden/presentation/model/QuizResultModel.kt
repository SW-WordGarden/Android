package com.sw.wordgarden.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class QuizResultModel(
    val userAnswer: String?,
    val correct: Boolean?,
    val time: Timestamp?,
    val questionNumber: Int?,
) : Parcelable

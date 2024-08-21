package com.sw.wordgarden.domain.entity.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class SqresultEntity(
    val userAnswer: String?,
    val correct: Boolean?,
    val time: Timestamp?,
    val sqQnum: Int?,
) : Parcelable

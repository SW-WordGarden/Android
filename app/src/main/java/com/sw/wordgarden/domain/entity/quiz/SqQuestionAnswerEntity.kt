package com.sw.wordgarden.domain.entity.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SqQuestionAnswerEntity(
    val question: String?,
    val answer: String?,
    val sqQnum: Int?,
) : Parcelable
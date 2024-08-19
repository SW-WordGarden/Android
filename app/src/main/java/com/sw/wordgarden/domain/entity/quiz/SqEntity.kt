package com.sw.wordgarden.domain.entity.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SqEntity(
    val uid: String?,
    val quizTitle: String?,
    val sqId: String?,
    val questionsAndAnswers: List<SqQuestionAnswerEntity>?,
    val sqresults: List<SqresultEntity>?,
) : Parcelable
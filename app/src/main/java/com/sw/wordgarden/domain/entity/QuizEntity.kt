package com.sw.wordgarden.domain.entity

import java.util.Date

data class QuizEntity(
    val id: String?,
    val question: String?,
    val answer: String?,
    val answerNumber: Int?,
    val check: Boolean?, //0: incorrect, 1: correct
    val checkTime: Date?,
)
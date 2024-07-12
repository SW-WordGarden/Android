package com.sw.wordgarden.data.dto

import java.util.Date

data class QuizDto(
    val id: String?,
    val question: String?,
    val answer: String?,
    val answerNumber: Int?,
    val check: Boolean?,
    val checkTime: Date?,
)
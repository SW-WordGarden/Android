package com.sw.wordgarden.domain.entity

import java.sql.Timestamp

data class QuizResultEntity(
    val userAnswer: String?,
    val correct: Boolean?,
    val time: Timestamp?,
    val answerNumber: Int?,
)

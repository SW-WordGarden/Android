package com.sw.wordgarden.domain.entity

data class SolveQuizEntity(
    val uid: String?,
    val quizTitle: String?,
    val answers: List<AnswerEntity>?,
)
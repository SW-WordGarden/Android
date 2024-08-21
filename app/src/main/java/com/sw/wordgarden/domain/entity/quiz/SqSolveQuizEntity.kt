package com.sw.wordgarden.domain.entity.quiz

data class SqSolveQuizEntity(
    val uid: String?,
    val quizTitle: String?,
    val answers: List<SqAnswerEntity>?,
)
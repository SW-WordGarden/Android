package com.sw.wordgarden.domain.entity.quiz

data class GroupedSqEntity(
    val uid: String?,
    val quizTitle: String?,
    val questionsAndAnswers: List<SqQuestionAnswerEntity>?,
)
package com.sw.wordgarden.domain.entity.quiz

data class WqSubmissionEntity(
    val uid: String?,
    val answers: List<WqAnswerEntity>?,
)
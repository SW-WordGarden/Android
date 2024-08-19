package com.sw.wordgarden.domain.entity.quiz

data class ShareRequestEntity(
    val fromUserId: String?,
    val toUserId: String?,
    val quizId: String?,
)
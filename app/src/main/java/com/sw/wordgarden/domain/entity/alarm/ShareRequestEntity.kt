package com.sw.wordgarden.domain.entity.alarm

data class ShareRequestEntity(
    val fromUserId: String?,
    val toUserId: String?,
    val quizId: String?,
)
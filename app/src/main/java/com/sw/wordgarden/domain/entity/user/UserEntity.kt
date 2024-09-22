package com.sw.wordgarden.domain.entity.user

data class UserEntity(
    val uid: String?,
    val uRank: Int?,
    val uPoint: Int?,
    val uName: String?,
    val uImage: String?,
    val uProvider: String?,
    val uParticipate: Int?,
)
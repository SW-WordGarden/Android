package com.sw.wordgarden.domain.entity.user

data class FriendListEntity(
    val userUUrl: String?,
    val friends: List<FriendEntity>?,
)
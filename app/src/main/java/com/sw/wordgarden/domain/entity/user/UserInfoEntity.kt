package com.sw.wordgarden.domain.entity.user

data class UserInfoEntity(
    val profileImage: String?,
    val point: Int?,
    val rank: Int?,
    val randomFriends: List<FriendEntity>?,
    val name: String?,
    val uUrl: String?,
    val all: Int?,
    val right: Int?,
    val latestCustomQuiz: CustomQuizEntity?,
    val latestSolvedQuiz: SolvedQuizEntity?,
)

data class CustomQuizEntity(
    val sqTitle: String?,
    val sqId: String?
)

data class SolvedQuizEntity(
    val type: String?,
    val title: String?
)
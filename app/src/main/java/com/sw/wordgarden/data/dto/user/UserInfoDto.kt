package com.sw.wordgarden.data.dto.user

import com.google.gson.annotations.SerializedName

data class UserInfoDto(
    @SerializedName("profileImage") val profileImage: String?,
    @SerializedName("point") val point: Int?,
    @SerializedName("rank") val rank: Int?,
    @SerializedName("randomFriends") val randomFriends: List<String>?,
    @SerializedName("name") val name: String?,
    @SerializedName("all") val all: Int?,
    @SerializedName("right") val right: Int?,
    @SerializedName("latestCustomQuiz") val latestCustomQuiz: CustomQuizDto?,
    @SerializedName("latestSolvedQuiz") val latestSolvedQuiz: SolvedQuizDto?,
)

data class CustomQuizDto(
    @SerializedName("sqTitle") val sqTitle: String?,
    @SerializedName("sqId") val sqId: String?
)

data class SolvedQuizDto(
    @SerializedName("quizId") val quizId: String?,
    @SerializedName("quizTitle") val quizTitle: String?
)
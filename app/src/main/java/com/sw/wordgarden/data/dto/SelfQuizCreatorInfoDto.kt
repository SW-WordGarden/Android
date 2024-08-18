package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName

data class SelfQuizCreatorInfoDto(
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("quizTitle") val quizTitle: String?,
)
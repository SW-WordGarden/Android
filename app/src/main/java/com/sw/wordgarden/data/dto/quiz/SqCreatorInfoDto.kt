package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class SqCreatorInfoDto(
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("quizTitle") val quizTitle: String?,
)
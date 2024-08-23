package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class SqCreatedInfoDto(
    @SerializedName("sqId") val sqId: String?,
    @SerializedName("quizTitle") val quizTitle: String?,
)
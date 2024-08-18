package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName

data class AnswerDto(
    @SerializedName("questionId") val questionId: Long?,
    @SerializedName("userAnswer") val userAnswer: String?,
)
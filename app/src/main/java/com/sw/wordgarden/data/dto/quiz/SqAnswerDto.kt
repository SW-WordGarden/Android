package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class SqAnswerDto(
    @SerializedName("questionId") val questionId: Long?,
    @SerializedName("userAnswer") val userAnswer: String?,
)
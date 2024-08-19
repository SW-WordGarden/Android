package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class WqStateDto(
    @SerializedName("totalQuestions") val totalQuestions: Long?,
    @SerializedName("correctAnswers") val correctAnswers: Long?,
)
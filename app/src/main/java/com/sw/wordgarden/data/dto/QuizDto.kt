package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName

data class QuizDto(
    @SerializedName("question") val question: String?,
    @SerializedName("answer") val answer: String?,
    @SerializedName("sqQnum") val questionNumber: Int?,
)
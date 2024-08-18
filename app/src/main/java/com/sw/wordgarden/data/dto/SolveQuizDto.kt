package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName

data class SolveQuizDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("quizTitle") val quizTitle: String?,
    @SerializedName("answers") val answers: List<AnswerDto>?,
)
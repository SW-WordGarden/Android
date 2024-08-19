package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class SqSolveQuizDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("quizTitle") val quizTitle: String?,
    @SerializedName("answers") val answers: List<SqAnswerDto>?,
)
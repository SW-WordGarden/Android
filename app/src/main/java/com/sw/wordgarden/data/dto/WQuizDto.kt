package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName

data class WQuizDto(
    @SerializedName("wqId") val quizId: String?,
    @SerializedName("uid") val uid: String?,
    @SerializedName("quizTitle") val title: String?,
    @SerializedName("questionsAndAnswers") val quiz: List<QuestionDto>?,
    @SerializedName("wqresults") val quizResult : List<QuestionResultDto>?,
)
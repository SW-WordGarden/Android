package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName

data class SelfQuizDto(
    @SerializedName("sqId") val quizId: String?,
    @SerializedName("uid") val uid: String?,
    @SerializedName("quizTitle") val title: String?,
    @SerializedName("questionsAndAnswers") val quiz: List<QuestionDto>?,
    @SerializedName("sqresults") val quizResult : List<QuestionResultDto>?,
)
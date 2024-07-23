package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName

data class QuizListDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("quizTitle") val title: String?,
    @SerializedName("questionsAndAnswers") val quiz: List<QuizDto>?,
    @SerializedName("sqresults") val quizResult : List<QuizResultDto>?,
)
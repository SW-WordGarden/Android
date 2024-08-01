package com.sw.wordgarden.domain.entity

import com.google.gson.annotations.SerializedName

data class QuizListEntity(
    @SerializedName("sqQnum") val title: String?,
    @SerializedName("sqQnum") val quiz: List<QuizEntity>?,
    @SerializedName("sqQnum") val quizResult: List<QuizResultEntity>?,
)
package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class QuizSummaryDto(
    @SerializedName("sqId") val quizId: String?,
    @SerializedName("title") val title: String?,
)
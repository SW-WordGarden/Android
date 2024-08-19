package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class SqQuestionDto(
    @SerializedName("id") val id: Long?,
    @SerializedName("question") val question: String?,
    @SerializedName("questionNumber") val questionNumber: Int?,
)
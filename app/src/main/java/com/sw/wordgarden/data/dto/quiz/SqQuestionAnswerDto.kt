package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class SqQuestionAnswerDto(
    @SerializedName("question") val question: String?,
    @SerializedName("answer") val answer: String?,
    @SerializedName("sqQnum") val sqQnum: Int?,
)
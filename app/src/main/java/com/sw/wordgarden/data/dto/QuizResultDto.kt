package com.sw.wordgarden.data.dto

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class QuizResultDto(
    @SerializedName("userAnswer") val userAnswer: String?,
    @SerializedName("correct") val correct: Boolean?,
    @SerializedName("time") val time: Timestamp?,
    @SerializedName("sqQnum") val answerNumber: Int?,
)

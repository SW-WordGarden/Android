package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class WqResponseDto(
    @SerializedName("wqId") val wqId: String?,
    @SerializedName("wqQuestion") val wqQuestion: String?,
    @SerializedName("wqTitle") val wqTitle: String?,
    @SerializedName("wordId") val wordId: String?,
    @SerializedName("word") val word: String?,
    @SerializedName("questionType") val questionType: String?,
    @SerializedName("options") val options : List<String>?,
    @SerializedName("userAnswer") val userAnswer: String?,
    @SerializedName("correctAnswer") val correctAnswer: String?,
)
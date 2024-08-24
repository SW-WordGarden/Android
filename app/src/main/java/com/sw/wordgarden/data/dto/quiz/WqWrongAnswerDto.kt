package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class WqWrongAnswerDto(
    @SerializedName("wqId") val wqId: String?,
    @SerializedName("word") val word: String?,
    @SerializedName("wordInfo") val wordInfo: String?,
)
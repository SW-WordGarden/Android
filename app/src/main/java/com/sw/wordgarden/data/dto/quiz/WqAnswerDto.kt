package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class WqAnswerDto(
    @SerializedName("wqId") val wqId: String?,
    @SerializedName("uWqA") val uWqA: String?,
)
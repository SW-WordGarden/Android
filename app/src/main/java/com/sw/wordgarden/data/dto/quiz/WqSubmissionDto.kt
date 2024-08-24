package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class WqSubmissionDto(
    @SerializedName("uid") val uid: String?,
    @SerializedName("answers") val answers: List<WqAnswerDto>?,
)
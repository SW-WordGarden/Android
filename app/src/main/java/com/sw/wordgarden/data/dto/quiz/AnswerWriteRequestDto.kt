package com.sw.wordgarden.data.dto.quiz

import com.google.gson.annotations.SerializedName

data class AnswerWriteRequestDto(
    @SerializedName("includeWrite") val includeWrite: Boolean?,
)
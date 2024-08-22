package com.sw.wordgarden.data.dto.alarm

import com.google.gson.annotations.SerializedName

data class ShareRequestDto(
    @SerializedName("fromUserId") val fromUserId: String?,
    @SerializedName("toUserId") val toUserId: String?,
    @SerializedName("quizId") val quizId: String?,
)
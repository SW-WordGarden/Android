package com.sw.wordgarden.data.dto.alarm

import com.google.gson.annotations.SerializedName

data class AlarmDetailDto (
    @SerializedName("alarmId") val alarmId: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("fromUserName") val fromUserName: String?,
)
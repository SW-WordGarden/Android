package com.sw.wordgarden.data.dto.alarm

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class AlarmDto (
    @SerializedName("alarmId") val alarmId: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("isRead") val isRead: Boolean?,
    @SerializedName("createTime") val createTime: LocalDateTime?,
    @SerializedName("fromUserName") val fromUserName: String?,
    @SerializedName("toUserName") val toUserName: String?,
)
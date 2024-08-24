package com.sw.wordgarden.data.dto.alarm

import com.google.gson.annotations.SerializedName

data class AlarmDto (
    @SerializedName("alarmId") val alarmId: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("isRead") val isRead: Boolean?,
    @SerializedName("createTime") val createTime: String?,
    @SerializedName("fromUserUid") val fromUserUid: String?,
    @SerializedName("fromUserName") val fromUserName: String?,
    @SerializedName("toUserName") val toUserName: String?,
    @SerializedName("quizType") val quizType: String?,
)
package com.sw.wordgarden.domain.entity.alarm

data class AlarmEntity (
    val alarmId: String?,
    val content: String?,
    val isRead: Boolean?,
    val createTime: String?,
    val fromUserUid: String?,
    val fromUserName: String?,
    val toUserName: String?,
    val quizType: String?,
)
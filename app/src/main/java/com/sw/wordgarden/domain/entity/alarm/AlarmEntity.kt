package com.sw.wordgarden.domain.entity.alarm

import java.time.LocalDateTime

data class AlarmEntity (
    val alarmId: String?,
    val content: String?,
    val isRead: Boolean?,
    val createTime: LocalDateTime?,
    val fromUserName: String?,
    val toUserName: String?,
)
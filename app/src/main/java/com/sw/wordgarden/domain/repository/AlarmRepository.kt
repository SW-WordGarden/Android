package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.alarm.AlarmDetailEntity
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity

interface AlarmRepository {
    suspend fun makeSharingAlarm(toUserId: String, quizId: String)
    suspend fun getAlarms(): List<AlarmEntity>?
    suspend fun getAlarmDetail(alarmId: String): AlarmDetailEntity?
    suspend fun deleteAlarm(alarmId: String)
}
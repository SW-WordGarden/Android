package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.alarm.AlarmDetailEntity
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity
import com.sw.wordgarden.domain.entity.alarm.ShareRequestEntity

interface AlarmRepository {
    suspend fun makeSharingAlarm(shareRequest: ShareRequestEntity)
    suspend fun getAlarms(): List<AlarmEntity>?
    suspend fun getAlarmDetail(alarmId: String): AlarmDetailEntity?
    suspend fun deleteAlarm(alarmId: String)
}
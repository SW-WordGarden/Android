package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.alarm.AlarmDetailEntity
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity
import com.sw.wordgarden.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : AlarmRepository {
    override suspend fun makeSharingAlarm(toUserId: String, quizId: String) {
        serverDataSource.makeSharingAlarm(toUserId, quizId)
    }

    override suspend fun getAlarms(): List<AlarmEntity>? {
        return serverDataSource.getAlarms()?.map { it.toEntity() }
    }

    override suspend fun getAlarmDetail(alarmId: String): AlarmDetailEntity? {
        return serverDataSource.getAlarmDetail(alarmId)?.toEntity()
    }

    override suspend fun deleteAlarm(alarmId: String) {
        serverDataSource.deleteAlarm(alarmId)
    }

}
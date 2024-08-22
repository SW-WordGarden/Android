package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.alarm.AlarmDetailEntity
import com.sw.wordgarden.domain.entity.alarm.AlarmEntity
import com.sw.wordgarden.domain.repository.AlarmRepository
import com.sw.wordgarden.domain.entity.alarm.ShareRequestEntity
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : AlarmRepository {
    override suspend fun makeSharingAlarm(shareRequest: ShareRequestEntity) {
        serverDataSource.makeSharingAlarm(shareRequest.toDto())
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
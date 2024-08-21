package com.sw.wordgarden.domain.usecase.datastore

import com.sw.wordgarden.domain.entity.alarm.AlarmEntity

interface GetAlarmListUseCase {
    suspend operator fun invoke(): List<AlarmEntity>?
}
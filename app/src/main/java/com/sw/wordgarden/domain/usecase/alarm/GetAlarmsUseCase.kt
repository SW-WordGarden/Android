package com.sw.wordgarden.domain.usecase.alarm

import com.sw.wordgarden.domain.entity.alarm.AlarmEntity

interface GetAlarmsUseCase {
    suspend operator fun invoke(): List<AlarmEntity>?
}
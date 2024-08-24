package com.sw.wordgarden.domain.usecase.alarm

import com.sw.wordgarden.domain.entity.alarm.AlarmDetailEntity

interface GetAlarmDetailUseCase {
    suspend operator fun invoke(alarmId: String): AlarmDetailEntity?
}
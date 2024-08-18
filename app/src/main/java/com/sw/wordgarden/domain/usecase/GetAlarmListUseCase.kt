package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.AlarmEntity

interface GetAlarmListUseCase {
    suspend operator fun invoke(): List<AlarmEntity>?
}
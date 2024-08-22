package com.sw.wordgarden.domain.usecase.alarm

import com.sw.wordgarden.domain.entity.alarm.AlarmEntity
import com.sw.wordgarden.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAlarmsUseCaseImpl @Inject constructor(
    private val alarmRepository: AlarmRepository
) : GetAlarmsUseCase {
    override suspend fun invoke(): List<AlarmEntity>? {
        return alarmRepository.getAlarms()
    }
}
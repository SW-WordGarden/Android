package com.sw.wordgarden.domain.usecase.alarm

import com.sw.wordgarden.domain.entity.alarm.AlarmDetailEntity
import com.sw.wordgarden.domain.repository.AlarmRepository
import javax.inject.Inject

class GetAlarmDetailUseCaseImpl @Inject constructor(
    private val alarmRepository: AlarmRepository
) : GetAlarmDetailUseCase {
    override suspend fun invoke(alarmId: String): AlarmDetailEntity? {
        return alarmRepository.getAlarmDetail(alarmId)
    }
}
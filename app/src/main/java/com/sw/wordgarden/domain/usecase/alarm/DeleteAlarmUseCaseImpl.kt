package com.sw.wordgarden.domain.usecase.alarm

import com.sw.wordgarden.domain.repository.AlarmRepository
import javax.inject.Inject

class DeleteAlarmUseCaseImpl @Inject constructor(
    private val alarmRepository: AlarmRepository
) : DeleteAlarmUseCase {
    override suspend fun invoke(alarmId: String) {
        alarmRepository.deleteAlarm(alarmId)
    }
}
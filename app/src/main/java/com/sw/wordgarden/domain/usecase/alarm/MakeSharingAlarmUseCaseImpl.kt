package com.sw.wordgarden.domain.usecase.alarm

import com.sw.wordgarden.domain.repository.AlarmRepository
import javax.inject.Inject

class MakeSharingAlarmUseCaseImpl @Inject constructor(
    private val alarmRepository: AlarmRepository
) : MakeSharingAlarmUseCase {
    override suspend fun invoke(toUserId: String, quizId: String) {
        alarmRepository.makeSharingAlarm(toUserId, quizId)
    }
}
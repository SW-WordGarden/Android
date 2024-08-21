package com.sw.wordgarden.domain.usecase.alarm

interface DeleteAlarmUseCase {
    suspend operator fun invoke(alarmId: String)
}
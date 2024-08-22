package com.sw.wordgarden.domain.usecase.alarm

interface MakeSharingAlarmUseCase {
    suspend operator fun invoke(toUserId: String, quizId: String)
}
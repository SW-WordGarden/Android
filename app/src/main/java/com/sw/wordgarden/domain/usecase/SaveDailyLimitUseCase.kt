package com.sw.wordgarden.domain.usecase

interface SaveDailyLimitUseCase {
    suspend operator fun invoke(count: Int)
}
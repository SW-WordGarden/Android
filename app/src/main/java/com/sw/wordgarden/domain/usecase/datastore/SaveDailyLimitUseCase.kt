package com.sw.wordgarden.domain.usecase.datastore

interface SaveDailyLimitUseCase {
    suspend operator fun invoke(count: Int)
}
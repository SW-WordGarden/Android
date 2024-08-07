package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveDailyLimitUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : SaveDailyLimitUseCase {
    override suspend fun invoke(count: Int) {
        dataStoreRepository.saveDailyLimit(count)
    }
}
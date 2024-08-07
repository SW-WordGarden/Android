package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class GetDailyLimitUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : GetDailyLimitUseCase {
    override suspend fun invoke(): Int? {
        return dataStoreRepository.getDailyLimit()
    }
}
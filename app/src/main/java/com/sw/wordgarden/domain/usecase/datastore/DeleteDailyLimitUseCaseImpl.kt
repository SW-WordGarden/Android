package com.sw.wordgarden.domain.usecase.datastore

import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class DeleteDailyLimitUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : DeleteDailyLimitUseCase {
    override suspend fun invoke() {
        dataStoreRepository.deleteDailyLimit()
    }
}
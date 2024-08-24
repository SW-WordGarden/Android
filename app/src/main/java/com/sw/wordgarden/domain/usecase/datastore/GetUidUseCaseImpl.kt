package com.sw.wordgarden.domain.usecase.datastore

import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class GetUidUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : GetUidUseCase {
    override suspend fun invoke(): String? {
        return dataStoreRepository.getUid()
    }
}
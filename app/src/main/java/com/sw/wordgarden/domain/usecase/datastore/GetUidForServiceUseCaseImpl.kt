package com.sw.wordgarden.domain.usecase.datastore

import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class GetUidForServiceUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : GetUidForServiceUseCase {
    override suspend fun invoke(): String? {
        return dataStoreRepository.getUidForService()
    }
}
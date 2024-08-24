package com.sw.wordgarden.domain.usecase.datastore

import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class DeleteUidUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : DeleteUidUseCase {
    override suspend fun invoke() {
        dataStoreRepository.deleteUid()
    }
}
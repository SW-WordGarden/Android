package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveUidUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : SaveUidUseCase {
    override suspend fun invoke(uid: String) {
        dataStoreRepository.saveUid(uid)
    }
}
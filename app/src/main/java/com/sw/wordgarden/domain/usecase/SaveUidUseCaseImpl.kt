package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.UidRepository
import javax.inject.Inject

class SaveUidUseCaseImpl @Inject constructor(
    private val uidRepository: UidRepository
) : SaveUidUseCase {
    override suspend fun invoke(uid: String) {
        uidRepository.saveUid(uid)
    }
}
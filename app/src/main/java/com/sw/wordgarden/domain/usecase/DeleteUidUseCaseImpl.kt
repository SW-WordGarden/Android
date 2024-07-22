package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.UidRepository
import javax.inject.Inject

class DeleteUidUseCaseImpl @Inject constructor(
    private val uidRepository: UidRepository
) : DeleteUidUseCase {
    override suspend fun invoke() {
        uidRepository.deleteUid()
    }
}
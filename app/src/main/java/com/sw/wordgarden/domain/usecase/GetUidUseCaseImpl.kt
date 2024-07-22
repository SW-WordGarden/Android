package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.UidRepository
import javax.inject.Inject

class GetUidUseCaseImpl @Inject constructor(
    private val uidRepository: UidRepository
) : GetUidUseCase {
    override suspend fun invoke(): String? {
        return uidRepository.getUid()
    }
}
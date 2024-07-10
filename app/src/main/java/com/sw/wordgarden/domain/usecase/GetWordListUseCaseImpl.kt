package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.WordEntity

class GetWordListUseCaseImpl: GetWordListUseCase {
    override suspend fun invoke(): List<WordEntity>? {
        TODO("Not yet implemented")
    }
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class GetWordListUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) : GetWordListUseCase {
    override suspend fun invoke(): List<WordEntity>? {
        TODO("Not yet implemented")
    }
}
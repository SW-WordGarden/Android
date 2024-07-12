package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class InsertLikedWordUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) : InsertLikedWordUseCase {
    override suspend fun invoke(word: WordEntity) {
        TODO("Not yet implemented")
    }
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class DeleteLikedWordUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) : DeleteLikedWordUseCase {
    override suspend fun invoke(wordId: String) {
        TODO("Not yet implemented")
    }
}
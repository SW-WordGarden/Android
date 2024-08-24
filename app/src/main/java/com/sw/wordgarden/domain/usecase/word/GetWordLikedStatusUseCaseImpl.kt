package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class GetWordLikedStatusUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) : GetWordLikedStatusUseCase{
    override suspend fun invoke(wordId: String): Boolean? {
        return wordRepository.getWordLikedStatus(wordId)
    }
}
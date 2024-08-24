package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class InsertLikedWordUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) : InsertLikedWordUseCase{
    override suspend fun invoke(wordId: String, isLiked: Boolean) {
        return wordRepository.insertLikedWord(wordId, isLiked)
    }
}
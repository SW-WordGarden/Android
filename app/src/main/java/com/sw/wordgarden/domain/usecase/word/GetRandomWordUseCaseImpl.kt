package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class GetRandomWordUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) :GetRandomWordUseCase{
    override suspend fun invoke(): WordEntity? {
        return wordRepository.getRandomWord()
    }
}
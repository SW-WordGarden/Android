package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class GetDetailWordUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) : GetDetailWordUseCase{
    override suspend fun invoke(wordId:String): WordEntity? {
        return wordRepository.getDetailWord(wordId)
    }
}
package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class GetWeeklyWordListUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) : GetWeeklyWordListUseCase{
    override suspend fun invoke(): List<WordEntity>? {
        return wordRepository.getWeeklyWordList()
    }
}
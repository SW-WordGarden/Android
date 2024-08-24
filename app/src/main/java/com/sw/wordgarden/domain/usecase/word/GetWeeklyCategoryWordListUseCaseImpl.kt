package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class GetWeeklyCategoryWordListUseCaseImpl @Inject constructor(
    private val wordRepository: WordRepository
) : GetWeeklyCategoryWordListUseCase{
    override suspend fun invoke(category: String): List<WordEntity>? {
        return wordRepository.getWeeklyCategoryWordList(category)
    }
}
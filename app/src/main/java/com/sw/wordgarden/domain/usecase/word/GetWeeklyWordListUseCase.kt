package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.entity.WordEntity

interface GetWeeklyWordListUseCase {
    suspend operator fun invoke():List<WordEntity>?
}
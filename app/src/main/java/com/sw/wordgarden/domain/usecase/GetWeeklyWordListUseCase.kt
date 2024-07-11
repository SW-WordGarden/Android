package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.WordEntity

interface GetWeeklyWordListUseCase {
    suspend operator fun invoke(): List<WordEntity>?
}
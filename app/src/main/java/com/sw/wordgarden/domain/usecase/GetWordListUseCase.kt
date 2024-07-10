package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.WordEntity

interface GetWordListUseCase {
    suspend operator fun invoke(): List<WordEntity>?

}
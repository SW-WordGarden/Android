package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.WordEntity

interface GetLikedWordListUseCase {
    suspend operator fun invoke(): List<WordEntity>?
}
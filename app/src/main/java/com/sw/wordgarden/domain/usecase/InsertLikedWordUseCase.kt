package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.WordEntity

interface InsertLikedWordUseCase {
    suspend operator fun invoke(word: WordEntity)
}
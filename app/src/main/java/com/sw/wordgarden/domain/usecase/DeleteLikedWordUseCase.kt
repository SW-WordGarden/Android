package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.WordEntity

interface DeleteLikedWordUseCase {
    suspend operator fun invoke(wordId: String)
}
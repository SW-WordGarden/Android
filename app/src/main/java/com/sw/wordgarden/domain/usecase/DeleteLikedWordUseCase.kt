package com.sw.wordgarden.domain.usecase

interface DeleteLikedWordUseCase {
    suspend operator fun invoke(wordId: String)
}
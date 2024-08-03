package com.sw.wordgarden.domain.usecase

interface SendFirebaseTokenUseCase {
    suspend operator fun invoke(token: String)
}
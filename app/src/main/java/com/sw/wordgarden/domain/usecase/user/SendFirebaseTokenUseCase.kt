package com.sw.wordgarden.domain.usecase.user

interface SendFirebaseTokenUseCase {
    suspend operator fun invoke(token: String)
}
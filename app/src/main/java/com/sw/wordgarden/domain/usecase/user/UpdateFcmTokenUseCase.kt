package com.sw.wordgarden.domain.usecase.user

interface UpdateFcmTokenUseCase {
    suspend operator fun invoke(token: String)
}
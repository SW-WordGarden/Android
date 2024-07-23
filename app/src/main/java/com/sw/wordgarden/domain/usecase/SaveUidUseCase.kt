package com.sw.wordgarden.domain.usecase

interface SaveUidUseCase {
    suspend operator fun invoke(uid: String)
}
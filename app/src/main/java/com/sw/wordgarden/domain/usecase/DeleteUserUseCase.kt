package com.sw.wordgarden.domain.usecase

interface DeleteUserUseCase {
    suspend operator fun invoke(uid: String)
}
package com.sw.wordgarden.domain.usecase.user

interface UpdateUserImageUseCase {
    suspend operator fun invoke(image: String)
}
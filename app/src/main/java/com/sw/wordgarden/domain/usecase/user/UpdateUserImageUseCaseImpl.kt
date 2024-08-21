package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserImageUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateUserImageUseCase {
    override suspend fun invoke(image: String) {
        userRepository.updateUserImage(image)
    }
}
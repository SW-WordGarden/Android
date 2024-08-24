package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class UpdateFcmTokenUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateFcmTokenUseCase {
    override suspend fun invoke(token: String) {
        userRepository.updateFcmToken(token)
    }
}
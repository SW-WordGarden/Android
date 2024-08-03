package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class SendFirebaseTokenUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : SendFirebaseTokenUseCase {
    override suspend fun invoke(token: String) {
        userRepository.sendFirebaseToken(token)
    }
}
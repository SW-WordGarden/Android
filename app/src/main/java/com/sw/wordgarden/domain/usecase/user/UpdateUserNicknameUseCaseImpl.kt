package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserNicknameUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : UpdateUserNicknameUseCase {
    override suspend fun invoke(nickname: String) {
        userRepository.updateUserNickname(nickname)
    }
}
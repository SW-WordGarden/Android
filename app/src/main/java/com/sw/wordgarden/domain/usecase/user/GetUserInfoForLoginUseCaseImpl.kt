package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.UserEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoForLoginUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserInfoForLoginUseCase {
    override suspend fun invoke(uid: String): UserEntity? {
        return userRepository.getUserInfoForLogin(uid)
    }
}
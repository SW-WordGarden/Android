package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.UserEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserInfoUseCase {
    override suspend fun invoke(uid: String): UserEntity? {
        return userRepository.getUserInfoForLogin(uid)
    }
}
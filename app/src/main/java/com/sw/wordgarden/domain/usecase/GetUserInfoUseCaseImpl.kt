package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.UserEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserInfoUseCase {
    override suspend fun invoke(): UserEntity? {
        TODO("Not yet implemented")
    }
}
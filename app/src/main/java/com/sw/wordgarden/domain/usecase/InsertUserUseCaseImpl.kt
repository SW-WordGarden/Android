package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.UserEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : InsertUserUseCase {
    override suspend fun invoke(userEntity: UserEntity) {
        TODO("Not yet implemented")
    }
}
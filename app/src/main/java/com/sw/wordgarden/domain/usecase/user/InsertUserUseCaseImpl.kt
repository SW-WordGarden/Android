package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : InsertUserUseCase {
    override suspend fun invoke(loginRequestEntity: LoginRequestEntity) {
        userRepository.insertUser(loginRequestEntity)
    }
}
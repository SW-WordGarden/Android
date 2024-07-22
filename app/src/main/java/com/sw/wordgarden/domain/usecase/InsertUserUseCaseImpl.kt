package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : InsertUserUseCase {
    override suspend fun invoke(signUpEntity: SignUpEntity) {
        userRepository.insertUser(signUpEntity)
    }
}
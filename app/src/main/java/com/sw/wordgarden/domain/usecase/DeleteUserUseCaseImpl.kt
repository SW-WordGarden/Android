package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : DeleteUserUseCase {
    override suspend fun invoke(uid: String) {
        TODO("Not yet implemented")
    }
}
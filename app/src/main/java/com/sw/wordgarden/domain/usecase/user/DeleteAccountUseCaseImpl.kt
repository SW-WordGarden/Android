package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class DeleteAccountUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : DeleteAccountUseCase {
    override suspend fun invoke() {
        userRepository.deleteAccout()
    }
}
package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class DeleteFriendUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : DeleteFriendUseCase {
    override suspend fun invoke(friendUrl: String) {
        userRepository.deleteFriend(friendUrl)
    }
}
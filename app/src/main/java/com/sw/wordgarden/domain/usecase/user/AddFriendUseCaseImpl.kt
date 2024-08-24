package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class AddFriendUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : AddFriendUseCase {
    override suspend fun invoke(friendUrl: String) {
        userRepository.addFriend(friendUrl)
    }
}
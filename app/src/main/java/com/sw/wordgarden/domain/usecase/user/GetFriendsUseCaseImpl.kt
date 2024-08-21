package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.FriendListEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class GetFriendsUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetFriendsUseCase {
    override suspend fun invoke(): FriendListEntity? {
        return userRepository.getFriends()
    }
}
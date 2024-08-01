package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.UserEntity
import com.sw.wordgarden.domain.repository.FriendRepository
import javax.inject.Inject

class GetFriendListUseCaseImpl @Inject constructor(
    private val friendRepository: FriendRepository
) : GetFriendListUseCase {
    override suspend fun invoke(): List<UserEntity>? {
        return friendRepository.getFriendList()
    }
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.FriendRepository
import javax.inject.Inject

class InsertFriendUseCaseImpl @Inject constructor(
    private val friendRepository: FriendRepository
) : InsertFriendUseCase {
    override suspend fun invoke(friendId: String) {
        TODO("Not yet implemented")
    }
}
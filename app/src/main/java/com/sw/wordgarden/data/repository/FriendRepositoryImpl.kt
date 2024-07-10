package com.sw.wordgarden.data.repository

import com.sw.wordgarden.domain.entity.UserEntity
import com.sw.wordgarden.domain.repository.FriendRepository

class FriendRepositoryImpl: FriendRepository {
    override suspend fun insertFriend(friendId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFriend(friendId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun reportFriend(friendId: String, contents: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getFriendList(): List<UserEntity>? {
        TODO("Not yet implemented")
    }
}
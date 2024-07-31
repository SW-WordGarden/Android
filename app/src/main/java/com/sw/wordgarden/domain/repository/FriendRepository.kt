package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.UserEntity

interface FriendRepository {
    suspend fun insertFriend(friendId: String)
    suspend fun deleteFriend(friendId: String)
    suspend fun reportFriend(friendId: String, contents: String)
    suspend fun getFriendList(): List<UserEntity>?
    suspend fun shareQuiz(quizTitle: String, friendUid: String)
}
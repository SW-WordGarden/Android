package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.user.FriendListEntity
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.domain.entity.user.UserEntity
import com.sw.wordgarden.domain.entity.user.UserInfoEntity

interface UserRepository {
    //login
    suspend fun insertUser(loginRequestEntity: LoginRequestEntity)
    suspend fun getUserInfoForLogin(uid: String): UserEntity?
    suspend fun updateFcmToken(token: String)

    //mapage
    suspend fun getUserInfoForMypage(): UserInfoEntity?
    suspend fun updateUserNickname(nickname: String)
    suspend fun updateUserImage(image: String)
    suspend fun getFriends(): FriendListEntity?
    suspend fun deleteAccout()

    //mypage - friend
    suspend fun addFriend(friendUrl: String)
    suspend fun deleteFriend(friendUrl: String)
    suspend fun reportUser(friendUid: String, contents: String?)
}
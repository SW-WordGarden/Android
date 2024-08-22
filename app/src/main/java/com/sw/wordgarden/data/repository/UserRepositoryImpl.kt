package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.user.FriendListEntity
import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.domain.entity.user.UserEntity
import com.sw.wordgarden.domain.entity.user.UserInfoEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : UserRepository {

    //login
    override suspend fun insertUser(loginRequestEntity: LoginRequestEntity) {
        serverDataSource.insertUser(loginRequestEntity.toDto())
    }

    override suspend fun getUserInfoForLogin(uid: String): UserEntity? {
        return serverDataSource.getUserInfoForLogin(uid)?.toEntity()
    }

    override suspend fun updateFcmToken(token: String) {
        serverDataSource.updateFcmToken(token)
    }

    //mypage
    override suspend fun getUserInfoForMypage(): UserInfoEntity? {
        return serverDataSource.getUserInfoForMypage()?.toEntity()
    }

    override suspend fun updateUserNickname(nickname: String) {
        serverDataSource.updateUserNickname(nickname)
    }

    override suspend fun updateUserImage(image: String) {
        serverDataSource.updateUserImage(image)
    }

    override suspend fun getFriends(): FriendListEntity? {
        return serverDataSource.getFriends()?.toEntity()
    }

    //mypage - friend
    override suspend fun addFriend(friendUrl: String) {
        serverDataSource.addFriend(friendUrl)
    }

    override suspend fun deleteFriend(friendUrl: String) {
        serverDataSource.deleteFriend(friendUrl)
    }

    override suspend fun reportUser(friendUid: String, contents: String?) {
        serverDataSource.reportUser(friendUid, contents)
    }
}
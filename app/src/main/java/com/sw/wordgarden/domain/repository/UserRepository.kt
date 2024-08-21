package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.user.LoginRequestEntity
import com.sw.wordgarden.domain.entity.user.ReportInfoEntity
import com.sw.wordgarden.domain.entity.user.UserEntity
import com.sw.wordgarden.domain.entity.user.UserInfoEntity

interface UserRepository {
    //login
    suspend fun insertUser(loginRequestEntity: LoginRequestEntity)
    suspend fun getUserInfoForLogin(uid: String): UserEntity?
    suspend fun sendFirebaseToken(token: String)

    //mapage
    suspend fun getUserInfoForMypage(): UserInfoEntity?
    suspend fun updateUserNickname(nickname: String)
    suspend fun getFriends(): List<UserEntity>?
    suspend fun reportUser(reportInfo: ReportInfoEntity)
}
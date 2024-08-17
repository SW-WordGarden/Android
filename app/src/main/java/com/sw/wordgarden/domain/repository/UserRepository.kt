package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.entity.UserEntity

interface UserRepository {
    suspend fun insertUser(signUpEntity: SignUpEntity)
    suspend fun deleteUser()
    suspend fun updateUser(userDto: UserEntity)
    suspend fun getUserInfo(uid: String): UserEntity?
    suspend fun sendFirebaseToken(token: String)
}
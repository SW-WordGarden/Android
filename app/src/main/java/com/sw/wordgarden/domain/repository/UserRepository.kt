package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.UserEntity

interface UserRepository {
    suspend fun insertUser(userDto: UserEntity)
    suspend fun deleteUser()
    suspend fun updateUser(userDto: UserEntity)
    suspend fun getUserInfo(): UserEntity?
}
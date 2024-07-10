package com.sw.wordgarden.data.repository

import com.sw.wordgarden.domain.entity.UserEntity
import com.sw.wordgarden.domain.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    override suspend fun insertUser(userDto: UserEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userDto: UserEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(): UserEntity? {
        TODO("Not yet implemented")
    }
}
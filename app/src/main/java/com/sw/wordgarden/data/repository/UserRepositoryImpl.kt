package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.entity.UserEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : UserRepository {
    override suspend fun insertUser(signUpEntity: SignUpEntity) {
        serverDataSource.insertUser(signUpEntity.toDto())
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userDto: UserEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(uid: String): UserEntity? {
        return serverDataSource.getUserInfo(uid)?.toEntity()
    }
}
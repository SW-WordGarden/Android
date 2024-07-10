package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.UserEntity

class GetFriendListUseCaseImpl: GetFriendListUseCase {
    override suspend fun invoke(uid: String): List<UserEntity>? {
        TODO("Not yet implemented")
    }
}
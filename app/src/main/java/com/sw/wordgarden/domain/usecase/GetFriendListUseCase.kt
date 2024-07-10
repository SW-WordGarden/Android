package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.UserEntity

interface GetFriendListUseCase {
    suspend operator fun invoke(uid: String): List<UserEntity>?
}
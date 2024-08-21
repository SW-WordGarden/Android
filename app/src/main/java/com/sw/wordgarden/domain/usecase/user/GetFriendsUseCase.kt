package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.UserEntity

interface GetFriendsUseCase {
    suspend operator fun invoke(): List<UserEntity>?
}
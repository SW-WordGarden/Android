package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.UserEntity

interface GetUserInfoUseCase {
    suspend operator fun invoke(uid: String): UserEntity?
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.UserEntity

interface GetUserInfoUseCase {
    suspend operator fun invoke(uid: String): UserEntity?
}
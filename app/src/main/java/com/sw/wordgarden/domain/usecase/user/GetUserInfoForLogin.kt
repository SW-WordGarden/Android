package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.UserEntity

interface GetUserInfoForLogin {
    suspend operator fun invoke(uid: String): UserEntity?
}
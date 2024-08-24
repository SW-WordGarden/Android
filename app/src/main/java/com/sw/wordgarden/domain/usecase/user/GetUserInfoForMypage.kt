package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.UserInfoEntity

interface GetUserInfoForMypage {
    suspend operator fun invoke(): UserInfoEntity?
}
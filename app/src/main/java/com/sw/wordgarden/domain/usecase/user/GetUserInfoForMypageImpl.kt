package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.UserInfoEntity
import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoForMypageImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUserInfoForMypage {
    override suspend fun invoke(): UserInfoEntity? {
        return userRepository.getUserInfoForMypage()
    }
}
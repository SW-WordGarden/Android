package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.FriendListEntity
import com.sw.wordgarden.domain.entity.user.UserEntity

interface GetFriendsUseCase {
    suspend operator fun invoke(): FriendListEntity?
}
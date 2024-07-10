package com.sw.wordgarden.domain.usecase

interface DeleteFriendUseCase {
    suspend operator fun invoke(friendId: String)
}
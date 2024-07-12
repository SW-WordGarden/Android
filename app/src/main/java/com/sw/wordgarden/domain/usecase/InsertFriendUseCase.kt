package com.sw.wordgarden.domain.usecase

interface InsertFriendUseCase {
    suspend operator fun invoke(friendId: String)
}
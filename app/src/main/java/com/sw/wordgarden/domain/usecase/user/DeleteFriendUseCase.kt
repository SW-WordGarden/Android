package com.sw.wordgarden.domain.usecase.user

interface DeleteFriendUseCase {
    suspend operator fun invoke(friendUrl: String)
}
package com.sw.wordgarden.domain.usecase.user

interface AddFriendUseCase {
    suspend operator fun invoke(friendUrl: String)
}
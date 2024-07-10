package com.sw.wordgarden.domain.usecase

interface ReportFriendUseCase {
    suspend operator fun invoke(friendId: String, contents: String)
}
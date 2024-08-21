package com.sw.wordgarden.domain.usecase.user

interface ReportFriendUseCase {
    suspend operator fun invoke(friendUid: String, contents: String?)
}
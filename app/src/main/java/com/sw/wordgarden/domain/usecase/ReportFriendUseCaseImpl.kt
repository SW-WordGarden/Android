package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.FriendRepository
import javax.inject.Inject

class ReportFriendUseCaseImpl @Inject constructor(
    private val friendRepository: FriendRepository
) : ReportFriendUseCase {
    override suspend fun invoke(friendId: String, contents: String) {
        TODO("Not yet implemented")
    }
}
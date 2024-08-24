package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.repository.UserRepository
import javax.inject.Inject

class ReportFriendUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : ReportFriendUseCase {
    override suspend fun invoke(friendUid: String, contents: String?) {
        userRepository.reportUser(friendUid, contents)
    }
}
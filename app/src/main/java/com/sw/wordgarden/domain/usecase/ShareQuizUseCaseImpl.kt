package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.FriendRepository
import javax.inject.Inject

class ShareQuizUseCaseImpl @Inject constructor(
    private val friendRepository: FriendRepository
) : ShareQuizUseCase {
    override suspend fun invoke(quizId: String, friendUid: String) {
        friendRepository.shareQuiz(quizId, friendUid)
    }
}
package com.sw.wordgarden.domain.usecase.quiz.share

import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class ShareQuizUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : ShareQuizUseCase {
    override suspend fun invoke(quizId: String, friendUid: String) {
        quizRepository.shareQuiz(quizId, friendUid)
    }
}
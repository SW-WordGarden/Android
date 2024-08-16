package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class SendQuizAnswerUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : SendQuizAnswerUseCase {
    override suspend fun invoke(quizResult: SelfQuizEntity) {
        quizRepository.sendQuizAnswer(quizResult)
    }
}
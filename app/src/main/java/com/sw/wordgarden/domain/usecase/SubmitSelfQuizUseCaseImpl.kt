package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SolveQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class SubmitSelfQuizUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : SubmitSelfQuizUseCase {
    override suspend fun invoke(solvedQuizEntity: SolveQuizEntity) {
        quizRepository.submitSelfQuiz(solvedQuizEntity)
    }
}
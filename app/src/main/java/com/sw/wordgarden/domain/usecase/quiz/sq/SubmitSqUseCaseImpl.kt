package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqSolveQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class SubmitSqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : SubmitSqUseCase {
    override suspend fun invoke(solvedQuiz: SqSolveQuizEntity) {
        quizRepository.submitSq(solvedQuiz)
    }
}
package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSolvedSqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSolvedSqUseCase {
    override suspend fun invoke(sqId: String): SqEntity? {
        return quizRepository.getSolvedSq(sqId)
    }
}
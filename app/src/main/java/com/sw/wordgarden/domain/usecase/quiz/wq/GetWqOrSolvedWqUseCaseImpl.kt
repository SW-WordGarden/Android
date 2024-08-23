package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetWqOrSolvedWqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetWqOrSolvedWqUseCase {
    override suspend fun invoke(qTitle: String, isSolved: Boolean): List<WqResponseEntity>? {
        return quizRepository.getWqOrSolvedWq(qTitle, isSolved)
    }
}
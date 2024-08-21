package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSolvedWqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSolvedWqUseCase {
    override suspend fun invoke(qTitle: String): List<WqResponseEntity>? {
        return quizRepository.getSolvedWq(qTitle)
    }
}
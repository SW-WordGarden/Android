package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizSummaryEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizListMadeByUserUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizListMadeByUserUseCase {
    override suspend fun invoke(): List<QuizSummaryEntity>? {
        return quizRepository.getQuizListMadeByUser()
    }
}
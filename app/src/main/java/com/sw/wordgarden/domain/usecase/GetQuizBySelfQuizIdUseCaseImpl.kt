package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizBySelfQuizIdUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizBySelfQuizIdUseCase {
    override suspend fun invoke(quizId: String): SelfQuizEntity? {
        return quizRepository.getQuizBySelfQuizId(quizId)
    }
}
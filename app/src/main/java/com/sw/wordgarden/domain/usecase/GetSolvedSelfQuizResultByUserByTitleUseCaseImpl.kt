package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSolvedSelfQuizResultByUserByTitleUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSolvedSelfQuizResultByUserByTitleUseCase {
    override suspend fun invoke(title: String): SelfQuizEntity? {
        return quizRepository.getSolvedSelfQuizResult(title)
    }
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSolvedSelfQuizTitleListByUserUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSolvedSelfQuizTitleListByUserUseCase {
    override suspend fun invoke(): List<String>? {
        return quizRepository.getSolvedSelfQuizTitleList()
    }
}
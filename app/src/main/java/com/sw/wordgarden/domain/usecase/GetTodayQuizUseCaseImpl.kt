package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetTodayQuizUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetTodayQuizUseCase {
    override suspend fun invoke(): SelfQuizEntity? {
        return quizRepository.getTodayQuiz()
    }
}
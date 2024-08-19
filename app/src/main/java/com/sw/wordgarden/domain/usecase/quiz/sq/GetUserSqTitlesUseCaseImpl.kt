package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqQuizSummaryEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetUserSqTitlesUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetUserSqTitlesUseCase {
    override suspend fun invoke(): List<SqQuizSummaryEntity>? {
        return quizRepository.getUserSqTitles()
    }
}
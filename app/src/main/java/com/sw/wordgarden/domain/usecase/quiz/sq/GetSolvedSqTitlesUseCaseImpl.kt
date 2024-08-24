package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSolvedSqTitlesUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSolvedSqTitlesUseCase {
    override suspend fun invoke(): List<QuizSummaryEntity>? {
        return quizRepository.getSolvedSqTitles()
    }
}
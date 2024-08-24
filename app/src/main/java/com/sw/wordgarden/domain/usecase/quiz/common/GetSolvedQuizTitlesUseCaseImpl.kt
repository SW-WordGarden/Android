package com.sw.wordgarden.domain.usecase.quiz.common

import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSolvedQuizTitlesUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSolvedQuizTitlesUseCase {
    override suspend fun invoke(): List<QuizSummaryEntity> {
        val wqResponse = quizRepository.getSolvedWqTitles() ?: emptySet()
        val wqTitles = wqResponse.map { title ->
            QuizSummaryEntity(
                quizId = null,
                title = title,
            )
        }
        val sqTitles = quizRepository.getSolvedSqTitles() ?: emptyList()
        val combinedTitles = wqTitles + sqTitles

        return combinedTitles
    }
}
package com.sw.wordgarden.domain.usecase.quiz.common

import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity

interface GetSolvedQuizTitlesUseCase {
    suspend operator fun invoke(): List<QuizSummaryEntity>
}
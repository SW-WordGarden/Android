package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity

interface GetSolvedSqTitlesUseCase {
    suspend operator fun invoke(): List<QuizSummaryEntity>?
}
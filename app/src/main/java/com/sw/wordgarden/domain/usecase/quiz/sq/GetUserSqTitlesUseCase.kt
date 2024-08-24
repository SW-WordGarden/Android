package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity

interface GetUserSqTitlesUseCase {
    suspend operator fun invoke(): List<QuizSummaryEntity>?
}
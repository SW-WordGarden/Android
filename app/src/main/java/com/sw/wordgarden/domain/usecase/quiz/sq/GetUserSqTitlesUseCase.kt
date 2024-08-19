package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqQuizSummaryEntity

interface GetUserSqTitlesUseCase {
    suspend operator fun invoke(): List<SqQuizSummaryEntity>?
}
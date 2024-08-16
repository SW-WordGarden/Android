package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizSummaryEntity

interface GetQuizListMadeByUserUseCase {
    suspend operator fun invoke(): List<QuizSummaryEntity>?
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

interface GetTodayQuizUseCase {
    suspend operator fun invoke(): QuizListEntity?
}
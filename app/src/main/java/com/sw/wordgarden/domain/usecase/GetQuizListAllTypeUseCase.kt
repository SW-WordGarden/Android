package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

interface GetQuizListAllTypeUseCase {
    suspend operator fun invoke(): QuizListEntity?
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

interface GetQuizListByTypeUseCase {
    suspend operator fun invoke(type: Boolean): List<QuizListEntity>?
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

interface GetQuizListMadeByUserUseCase {
    suspend operator fun invoke(): List<QuizListEntity>?
}
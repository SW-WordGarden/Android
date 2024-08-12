package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

interface GetQuizListMadeByUserByTitleUseCase {
    suspend operator fun invoke(title: String): QuizListEntity?
}
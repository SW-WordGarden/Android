package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface GetQuizBySelfQuizIdUseCase {
    suspend operator fun invoke(quizId: String): SelfQuizEntity?
}
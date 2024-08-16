package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface GetTodayQuizUseCase {
    suspend operator fun invoke(): SelfQuizEntity?
}
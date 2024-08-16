package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface GetQuizListAllTypeUseCase {
    suspend operator fun invoke(): SelfQuizEntity?
}
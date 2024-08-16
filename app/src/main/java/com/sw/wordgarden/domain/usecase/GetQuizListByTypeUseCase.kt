package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface GetQuizListByTypeUseCase {
    suspend operator fun invoke(type: Boolean): SelfQuizEntity?
}
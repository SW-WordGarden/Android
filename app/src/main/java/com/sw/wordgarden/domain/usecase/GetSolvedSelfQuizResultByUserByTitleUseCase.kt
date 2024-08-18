package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface GetSolvedSelfQuizResultByUserByTitleUseCase {
    suspend operator fun invoke(title: String): SelfQuizEntity?
}
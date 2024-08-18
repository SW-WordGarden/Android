package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SolveQuizEntity

interface SubmitSelfQuizUseCase {
    suspend operator fun invoke(solvedQuizEntity: SolveQuizEntity)
}
package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity

interface GetSolvedWqUseCase {
    suspend operator fun invoke(qTitle: String): List<WqResponseEntity>?
}

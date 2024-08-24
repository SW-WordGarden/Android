package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity

interface GetWqOrSolvedWqUseCase {
    suspend operator fun invoke(qTitle: String, isSolved: Boolean): List<WqResponseEntity>?
}

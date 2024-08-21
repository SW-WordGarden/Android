package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqEntity

interface GetSolvedSqUseCase {
    suspend operator fun invoke(sqId: String): SqEntity?
}
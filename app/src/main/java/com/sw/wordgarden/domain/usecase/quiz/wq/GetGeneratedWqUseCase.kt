package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity

interface GetGeneratedWqUseCase {
    suspend operator fun invoke(): List<WqResponseEntity>?
}

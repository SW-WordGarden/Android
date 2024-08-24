package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqStateEntity

interface GetWqStateUseCase {
    suspend operator fun invoke(): WqStateEntity?
}

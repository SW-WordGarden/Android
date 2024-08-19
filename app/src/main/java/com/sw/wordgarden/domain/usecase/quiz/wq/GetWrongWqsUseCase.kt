package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqWrongAnswerEntity

interface GetWrongWqsUseCase {
    suspend operator fun invoke(): List<WqWrongAnswerEntity>?
}

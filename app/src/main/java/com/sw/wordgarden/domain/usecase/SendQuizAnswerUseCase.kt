package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface SendQuizAnswerUseCase {
    suspend operator fun invoke(quizResult: SelfQuizEntity)
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

interface SendQuizAnswerUseCase {
    suspend operator fun invoke(quizResult: QuizListEntity)
}
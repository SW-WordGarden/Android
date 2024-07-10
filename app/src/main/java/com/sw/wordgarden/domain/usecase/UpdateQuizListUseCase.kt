package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

interface UpdateQuizListUseCase {
    suspend operator fun invoke(quizList: QuizListEntity)
}
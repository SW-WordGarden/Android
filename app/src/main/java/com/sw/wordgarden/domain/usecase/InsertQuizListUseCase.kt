package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

interface InsertQuizListUseCase {
    suspend operator fun invoke(quizList: QuizListEntity)

}
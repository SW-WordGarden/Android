package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.presentation.model.QuizModel

interface SubmitSqUseCase {
    suspend operator fun invoke(quizModel: QuizModel)
}
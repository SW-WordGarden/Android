package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.presentation.model.QuizModel

interface SubmitWqUseCase {
    suspend operator fun invoke(quizModel: QuizModel)
}

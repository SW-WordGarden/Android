package com.sw.wordgarden.domain.usecase.quiz.common

interface ShareQuizUseCase {
    suspend operator fun invoke(quizId: String, friendUid: String)
}
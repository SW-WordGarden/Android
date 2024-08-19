package com.sw.wordgarden.domain.usecase.quiz.share

interface ShareQuizUseCase {
    suspend operator fun invoke(quizId: String, friendUid: String)
}
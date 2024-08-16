package com.sw.wordgarden.domain.usecase

interface ShareQuizUseCase {
    suspend operator fun invoke(quizId: String, friendUid: String)
}
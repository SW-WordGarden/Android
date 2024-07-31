package com.sw.wordgarden.domain.usecase

interface ShareQuizUseCase {
    suspend operator fun invoke(quizTitle: String, friendUid: String)
}
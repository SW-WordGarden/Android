package com.sw.wordgarden.domain.usecase

interface DeleteQuizListUseCase {
    suspend operator fun invoke(quizListId: String)
}
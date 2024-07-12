package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class DeleteQuizListUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : DeleteQuizListUseCase {
    override suspend fun invoke(quizListId: String) {
        TODO("Not yet implemented")
    }
}
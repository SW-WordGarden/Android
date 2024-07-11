package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class UpdateQuizListUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : UpdateQuizListUseCase {
    override suspend fun invoke(quizList: QuizListEntity) {
        TODO("Not yet implemented")
    }
}
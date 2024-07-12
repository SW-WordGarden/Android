package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class InsertQuizListUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : InsertQuizListUseCase {
    override suspend fun invoke(): QuizListEntity {
        TODO("Not yet implemented")
    }
}
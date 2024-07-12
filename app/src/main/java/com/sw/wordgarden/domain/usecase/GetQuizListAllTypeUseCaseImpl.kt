package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizListAllTypeUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizListAllTypeUseCase {
    override suspend fun invoke(): QuizListEntity? {
        TODO("Not yet implemented")
    }
}
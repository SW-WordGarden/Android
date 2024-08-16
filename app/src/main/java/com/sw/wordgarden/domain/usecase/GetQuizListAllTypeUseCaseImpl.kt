package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizListAllTypeUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizListAllTypeUseCase {
    override suspend fun invoke(): SelfQuizEntity? {
        TODO("Not yet implemented")
    }
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizListByTypeUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizListByTypeUseCase {
    override suspend fun invoke(type: Boolean): QuizListEntity? {
        TODO("Not yet implemented")
    }
}
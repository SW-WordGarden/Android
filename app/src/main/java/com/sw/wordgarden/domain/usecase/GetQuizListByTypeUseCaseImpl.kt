package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizListByTypeUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizListByTypeUseCase {
    override suspend fun invoke(type: Boolean): SelfQuizEntity? {
        TODO("Not yet implemented")
    }
}
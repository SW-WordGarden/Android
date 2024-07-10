package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity

class GetQuizListByTypeUseCaseImpl: GetQuizListByTypeUseCase {
    override suspend fun invoke(type: Boolean): List<QuizListEntity>? {
        TODO("Not yet implemented")
    }
}
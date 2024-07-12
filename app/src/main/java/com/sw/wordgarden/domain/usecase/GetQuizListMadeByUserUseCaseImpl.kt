package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizListMadeByUserUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizListMadeByUserUseCase {
    override suspend fun invoke(): List<QuizListEntity>? {
        TODO("Not yet implemented")
    }
}
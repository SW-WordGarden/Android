package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizListMadeByUserByQuizIdUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizListMadeByUserByQuizIdUseCase {
    override suspend fun invoke(quizId: String): SelfQuizEntity? {
        return quizRepository.getQuizListMadeByUserByQuizId(quizId)
    }
}
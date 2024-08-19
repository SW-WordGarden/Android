package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqCreatorInfoEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSqCreatorInfoUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSqCreatorInfoUseCase {
    override suspend fun invoke(quizId: String): SqCreatorInfoEntity? {
        return quizRepository.getSqCreatorInfo(quizId)
    }
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizCreatorInfoEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSelfQuizCreatorInfoByQuizIdUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSelfQuizCreatorInfoByQuizIdUseCase {
    override suspend fun invoke(quizId: String): SelfQuizCreatorInfoEntity? {
        return quizRepository.getSelfQuizCreatorInfo(quizId)
    }
}
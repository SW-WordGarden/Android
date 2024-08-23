package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqCreatedInfoEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class CreateNewSqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : CreateNewSqUseCase {
    override suspend fun invoke(sqEntity: SqEntity): SqCreatedInfoEntity? {
        return quizRepository.createNewSq(sqEntity)
    }
}
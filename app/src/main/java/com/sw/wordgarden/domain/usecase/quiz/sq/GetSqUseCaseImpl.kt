package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSqUseCase {
    override suspend fun invoke(quizId: String): List<SqQuestionAnswerEntity>? {
        return quizRepository.getSq(quizId)
    }
}
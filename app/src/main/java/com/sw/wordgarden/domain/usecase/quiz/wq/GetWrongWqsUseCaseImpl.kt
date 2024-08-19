package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqWrongAnswerEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetWrongWqsUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetWrongWqsUseCase {
    override suspend fun invoke(): List<WqWrongAnswerEntity>? {
        return quizRepository.getWrongWqs()
    }
}
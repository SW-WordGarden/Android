package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqStateEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetWqStateUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetWqStateUseCase {
    override suspend fun invoke(): WqStateEntity? {
        return quizRepository.getWqState()
    }
}
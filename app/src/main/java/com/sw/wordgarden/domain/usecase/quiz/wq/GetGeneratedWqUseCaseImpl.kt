package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetGeneratedWqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetGeneratedWqUseCase {
    override suspend fun invoke(): List<WqResponseEntity>? = quizRepository.getGeneratedWq()
}
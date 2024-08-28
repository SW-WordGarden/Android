package com.sw.wordgarden.domain.usecase.quiz

import com.sw.wordgarden.domain.entity.quiz.OneQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetOneQuizUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
):GetOneQuizUseCase{
    override suspend fun invoke(): OneQuizEntity? {
        return quizRepository.getLockQuiz()
    }

}
package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.repository.QuizRepository
import com.sw.wordgarden.presentation.mapper.ModelMapper.toSqSolveQuizEntity
import com.sw.wordgarden.presentation.model.QuizModel
import javax.inject.Inject

class SubmitSqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : SubmitSqUseCase {
    override suspend fun invoke(quizModel: QuizModel) {
        quizRepository.submitSq(quizModel.toSqSolveQuizEntity())
    }
}
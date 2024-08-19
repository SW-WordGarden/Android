package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.repository.QuizRepository
import com.sw.wordgarden.presentation.mapper.ModelMapper.toWqSubmissionEntity
import com.sw.wordgarden.presentation.model.QuizModel
import javax.inject.Inject

class SubmitWqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : SubmitWqUseCase {
    override suspend fun invoke(quizModel: QuizModel) {
        quizRepository.submitWq(quizModel.toWqSubmissionEntity())
    }
}
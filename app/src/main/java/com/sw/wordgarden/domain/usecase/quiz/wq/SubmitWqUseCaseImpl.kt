package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqSubmissionEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class SubmitWqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : SubmitWqUseCase {
    override suspend fun invoke(submissionQuiz: WqSubmissionEntity) {
        quizRepository.submitWq(submissionQuiz)
    }
}
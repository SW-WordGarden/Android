package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.entity.quiz.WqSubmissionEntity

interface SubmitWqUseCase {
    suspend operator fun invoke(submissionQuiz: WqSubmissionEntity)
}

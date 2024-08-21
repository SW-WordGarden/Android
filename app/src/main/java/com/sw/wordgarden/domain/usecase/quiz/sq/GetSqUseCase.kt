package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity

interface GetSqUseCase {
    suspend operator fun invoke(quizId: String): List<SqQuestionAnswerEntity>?
}
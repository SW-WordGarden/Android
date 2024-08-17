package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuestionAnswerEntity
import com.sw.wordgarden.domain.entity.QuizListEntity

interface CheckQuizResultUseCase {
    operator fun invoke(quiz: QuizListEntity, enteredAnswers: List<QuestionAnswerEntity>): QuizListEntity?
}
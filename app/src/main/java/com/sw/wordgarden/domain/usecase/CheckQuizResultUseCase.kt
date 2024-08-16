package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuestionAnswerEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface CheckQuizResultUseCase {
    operator fun invoke(quiz: SelfQuizEntity, enteredAnswers: List<QuestionAnswerEntity>): SelfQuizEntity?
}
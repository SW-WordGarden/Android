package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqSolveQuizEntity

interface SubmitSqUseCase {
    suspend operator fun invoke(solvedQuiz: SqSolveQuizEntity)
}
package com.sw.wordgarden.domain.usecase.quiz

import com.sw.wordgarden.domain.entity.quiz.OneQuizEntity

interface GetOneQuizUseCase {
    suspend operator fun invoke():OneQuizEntity?
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface GetQuizListMadeByUserByQuizIdUseCase {
    suspend operator fun invoke(quizId: String): SelfQuizEntity?
}
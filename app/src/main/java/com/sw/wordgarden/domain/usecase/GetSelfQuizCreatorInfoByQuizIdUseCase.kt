package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizCreatorInfoEntity

interface GetSelfQuizCreatorInfoByQuizIdUseCase {
    suspend operator fun invoke(quizId: String): SelfQuizCreatorInfoEntity?
}
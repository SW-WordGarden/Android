package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqCreatorInfoEntity

interface GetSqCreatorInfoUseCase {
    suspend operator fun invoke(quizId: String): SqCreatorInfoEntity?
}
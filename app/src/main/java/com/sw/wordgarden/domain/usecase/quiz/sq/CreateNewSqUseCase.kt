package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqCreatedInfoEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity

interface CreateNewSqUseCase {
    suspend operator fun invoke(sqEntity: SqEntity): SqCreatedInfoEntity?
}
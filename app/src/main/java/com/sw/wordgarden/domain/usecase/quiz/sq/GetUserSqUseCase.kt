package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqEntity

interface GetUserSqUseCase {
    suspend operator fun invoke(creatorUid: String, sqId: String): SqEntity?
}
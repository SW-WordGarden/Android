package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity

interface InsertQuizListUseCase {
    suspend operator fun invoke(quizList: SelfQuizEntity)

}
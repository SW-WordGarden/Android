package com.sw.wordgarden.domain.usecase.quiz.wq

interface GetSolvedWqTitlesUseCase {
    suspend operator fun invoke(): Set<String>?
}

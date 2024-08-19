package com.sw.wordgarden.domain.usecase.quiz.sq

interface GetSolvedSqTitlesUseCase {
    suspend operator fun invoke(): List<String>?
}
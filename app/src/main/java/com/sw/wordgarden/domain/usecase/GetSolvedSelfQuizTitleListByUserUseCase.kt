package com.sw.wordgarden.domain.usecase

interface GetSolvedSelfQuizTitleListByUserUseCase {
    suspend operator fun invoke(): List<String>?
}
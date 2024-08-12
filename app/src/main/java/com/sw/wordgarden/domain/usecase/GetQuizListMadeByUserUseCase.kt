package com.sw.wordgarden.domain.usecase

interface GetQuizListMadeByUserUseCase {
    suspend operator fun invoke(): List<String>?
}
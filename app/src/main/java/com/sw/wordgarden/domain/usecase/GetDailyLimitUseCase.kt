package com.sw.wordgarden.domain.usecase


interface GetDailyLimitUseCase {
    suspend operator fun invoke(): Int?
}
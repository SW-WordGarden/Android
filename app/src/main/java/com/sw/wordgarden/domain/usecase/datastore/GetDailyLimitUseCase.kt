package com.sw.wordgarden.domain.usecase.datastore


interface GetDailyLimitUseCase {
    suspend operator fun invoke(): Int?
}
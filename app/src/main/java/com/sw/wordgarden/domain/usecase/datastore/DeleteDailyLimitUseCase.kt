package com.sw.wordgarden.domain.usecase.datastore

interface DeleteDailyLimitUseCase {
    suspend operator fun invoke()
}
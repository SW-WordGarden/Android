package com.sw.wordgarden.domain.usecase.datastore


interface GetUidUseCase {
    suspend operator fun invoke(): String?
}
package com.sw.wordgarden.domain.usecase


interface GetUidUseCase {
    suspend operator fun invoke(): String?
}
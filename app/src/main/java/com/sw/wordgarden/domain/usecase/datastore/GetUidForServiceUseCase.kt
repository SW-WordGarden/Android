package com.sw.wordgarden.domain.usecase.datastore


interface GetUidForServiceUseCase {
    suspend operator fun invoke(): String?
}
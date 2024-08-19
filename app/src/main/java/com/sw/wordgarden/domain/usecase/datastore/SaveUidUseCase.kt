package com.sw.wordgarden.domain.usecase.datastore

interface SaveUidUseCase {
    suspend operator fun invoke(uid: String)
}
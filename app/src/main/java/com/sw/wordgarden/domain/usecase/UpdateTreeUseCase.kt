package com.sw.wordgarden.domain.usecase

interface UpdateTreeUseCase {
    suspend operator fun invoke(treeId: String)
}
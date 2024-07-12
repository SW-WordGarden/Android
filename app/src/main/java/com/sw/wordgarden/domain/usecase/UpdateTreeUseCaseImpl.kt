package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.repository.GardenRepository
import javax.inject.Inject

class UpdateTreeUseCaseImpl @Inject constructor(
    private val gardenRepository: GardenRepository
) : UpdateTreeUseCase {
    override suspend fun invoke(treeId: String) {
        TODO("Not yet implemented")
    }
}
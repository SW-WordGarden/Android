package com.sw.wordgarden.domain.usecase.garden

import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.repository.GardenRepository
import javax.inject.Inject

class GetGrowInfoUseCaseImpl @Inject constructor(
    private val gardenRepository: GardenRepository
) : GetGrowInfoUseCase{
    override suspend fun invoke(): TreeEntity? {
        return gardenRepository.getGrowInfo()
    }
}
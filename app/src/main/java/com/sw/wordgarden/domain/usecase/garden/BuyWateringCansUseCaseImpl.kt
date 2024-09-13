package com.sw.wordgarden.domain.usecase.garden

import com.sw.wordgarden.domain.repository.GardenRepository
import javax.inject.Inject

class BuyWateringCansUseCaseImpl @Inject constructor(
    private val gardenRepository: GardenRepository
) : BuyWateringCansUseCase{
    override suspend fun invoke() {
        return gardenRepository.buyWateringCans()
    }
}
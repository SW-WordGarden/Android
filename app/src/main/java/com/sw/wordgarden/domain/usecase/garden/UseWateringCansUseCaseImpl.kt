package com.sw.wordgarden.domain.usecase.garden

import com.sw.wordgarden.domain.repository.GardenRepository
import javax.inject.Inject

class UseWateringCansUseCaseImpl @Inject constructor(
    private val gardenRepository: GardenRepository
):UseWateringCansUseCase{
    override suspend fun invoke() {
        return gardenRepository.useWateringCans()
    }
}
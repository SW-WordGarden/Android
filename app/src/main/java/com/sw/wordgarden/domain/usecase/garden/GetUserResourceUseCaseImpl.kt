package com.sw.wordgarden.domain.usecase.garden

import com.sw.wordgarden.domain.entity.user.UserResponseEntity
import com.sw.wordgarden.domain.repository.GardenRepository
import javax.inject.Inject

class GetUserResourceUseCaseImpl @Inject constructor(
    private val gardenRepository: GardenRepository
):GetUserResourceUseCase{
    override suspend fun invoke(): UserResponseEntity? {
        return gardenRepository.getUserResource()
    }
}
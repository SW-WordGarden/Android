package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.repository.GardenRepository
import javax.inject.Inject

class GetTreeListUseCaseImpl @Inject constructor(
    private val gardenRepository: GardenRepository
) : GetTreeListUseCase {
    override suspend fun invoke(): List<TreeEntity>? {
        TODO("Not yet implemented")
    }
}
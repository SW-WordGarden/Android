package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.dto.TreeDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.repository.GardenRepository
import javax.inject.Inject

class GardenRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : GardenRepository {
    override suspend fun getGrowInfo(): TreeEntity? {
        return serverDataSource.getGrowInfo()?.toEntity()
    }

}
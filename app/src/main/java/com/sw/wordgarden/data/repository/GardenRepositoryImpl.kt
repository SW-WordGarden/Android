package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.repository.GardenRepository
import javax.inject.Inject

class GardenRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : GardenRepository {
    override suspend fun updateTree(treeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getTreeList(): List<TreeEntity>? {
        TODO("Not yet implemented")
    }
}
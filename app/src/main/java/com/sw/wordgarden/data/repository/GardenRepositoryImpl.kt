package com.sw.wordgarden.data.repository

import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.repository.GardenRepository

class GardenRepositoryImpl: GardenRepository {
    override suspend fun updateTree(treeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getTreeList(): List<TreeEntity>? {
        TODO("Not yet implemented")
    }
}
package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.TreeEntity

interface GardenRepository {
    suspend fun updateTree(treeId: String)
    suspend fun getTreeList(): List<TreeEntity>?
}
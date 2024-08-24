package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.TreeEntity

interface GardenRepository {
    suspend fun getGrowInfo(): TreeEntity?
}
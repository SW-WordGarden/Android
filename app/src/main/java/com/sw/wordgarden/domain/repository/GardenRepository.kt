package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.TreeEntity
import com.sw.wordgarden.domain.entity.user.UserResponseEntity

interface GardenRepository {
    suspend fun getGrowInfo(): TreeEntity?
    suspend fun getUserResource() : UserResponseEntity?
    suspend fun buyWateringCans()
    suspend fun useWateringCans()
}
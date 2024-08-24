package com.sw.wordgarden.domain.repository

interface DataStoreRepository {

    //user
    suspend fun saveUid(uid: String)
    suspend fun getUid(): String?
    suspend fun getUidForService(): String?
    suspend fun deleteUid()

    //quiz
    suspend fun saveDailyLimit(count: Int)
    suspend fun getDailyLimit(): Int?
    suspend fun deleteDailyLimit()
}
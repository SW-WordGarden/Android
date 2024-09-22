package com.sw.wordgarden.domain.repository

interface DataStoreRepository {

    //user
    suspend fun saveUid(uid: String)
    suspend fun getUid(): String?
    suspend fun getUidForService(): String?
    suspend fun deleteUid()
}
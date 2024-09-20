package com.sw.wordgarden.data.datasource.local

interface LocalDataSource {

    //user
    suspend fun saveUid(uid: String)
    suspend fun getUid(): String?
    suspend fun getUidForService(): String?
    suspend fun deleteUid()
}
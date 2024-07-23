package com.sw.wordgarden.data.datasource.local

interface LocalDataAuth {
    suspend fun saveUid(uid: String)
    suspend fun getUid(): String?
    suspend fun deleteUid()
}
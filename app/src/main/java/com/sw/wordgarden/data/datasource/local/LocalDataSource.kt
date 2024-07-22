package com.sw.wordgarden.data.datasource.local

import androidx.datastore.preferences.core.Preferences

interface LocalDataSource {
    suspend fun saveUid(uid: String)
    suspend fun getUid(): String?
    suspend fun deleteUid()
}
package com.sw.wordgarden.domain.repository

import androidx.datastore.preferences.core.Preferences

interface UidRepository {
    suspend fun saveUid(uid: String)
    suspend fun getUid(): String?
    suspend fun deleteUid()
}
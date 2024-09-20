package com.sw.wordgarden.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val preferencesStore: DataStore<Preferences>
) : LocalDataSource {

    private val USER_KEY = stringPreferencesKey("user_key")

    //user
    override suspend fun saveUid(uid: String) {
        preferencesStore.edit { preferences ->
            preferences[USER_KEY] = uid
        }
    }

    override suspend fun getUid(): String? {
        return preferencesStore.data.first()[USER_KEY]
    }

    override suspend fun getUidForService(): String? {
        return preferencesStore.data.first()[USER_KEY]
    }

    override suspend fun deleteUid() {
        preferencesStore.edit { preferences ->
            preferences.remove(USER_KEY)
        }
    }
}
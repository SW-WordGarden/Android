package com.sw.wordgarden.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalDataAuthImpl @Inject constructor(
    private val uidPreferencesStore: DataStore<Preferences>
) : LocalDataAuth {

    private val USER_KEY = stringPreferencesKey("user_key")

    override suspend fun saveUid(uid: String) {

        uidPreferencesStore.edit { preferences ->
            preferences[USER_KEY] = uid
        }
    }

    override suspend fun getUid(): String? {
        return uidPreferencesStore.data.first()[USER_KEY]
    }

    override suspend fun deleteUid() {
        uidPreferencesStore.edit { preferences ->
            preferences.clear()
        }
    }
}
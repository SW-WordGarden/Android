package com.sw.wordgarden.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sw.wordgarden.data.datasource.local.CalendarUtility.isSameDay
import com.sw.wordgarden.data.dto.alarm.AlarmDto
import kotlinx.coroutines.flow.first
import java.sql.Date
import java.util.Calendar
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val preferencesStore: DataStore<Preferences>
) : LocalDataSource {

    private val USER_KEY = stringPreferencesKey("user_key")
    private val DAILY_LIMIT_KEY = intPreferencesKey("daily_limit_key")
    private val DAILY_LIMIT_TIMESTAMP_KEY = longPreferencesKey("daily_limit_timestamp_key")

    //user
    override suspend fun saveUid(uid: String) {
        preferencesStore.edit { preferences ->
            preferences[USER_KEY] = uid
        }
    }

    override suspend fun getUid(): String? {
        return preferencesStore.data.first()[USER_KEY]
    }

    override suspend fun deleteUid() {
        preferencesStore.edit { preferences ->
            preferences.remove(USER_KEY)
        }
    }

    //quiz
    override suspend fun saveDailyLimit(count: Int) {
        val currentTime = System.currentTimeMillis()

        preferencesStore.edit { preferences ->
            preferences[DAILY_LIMIT_KEY] = (count + 1)
            preferences[DAILY_LIMIT_TIMESTAMP_KEY] = currentTime
        }
    }

    override suspend fun getDailyLimit(): Int {
        val preferences = preferencesStore.data.first()
        val timestamp = preferences[DAILY_LIMIT_TIMESTAMP_KEY] ?: 0L

        return if (isSameDay(timestamp)) {
            preferences[DAILY_LIMIT_KEY] ?: 0
        } else {
            deleteDailyLimit()
            0
        }
    }

    override suspend fun deleteDailyLimit() {
        preferencesStore.edit { preferences ->
            preferences.remove(DAILY_LIMIT_KEY)
            preferences.remove(DAILY_LIMIT_TIMESTAMP_KEY)
        }
    }

    override suspend fun getAlarmList(): List<AlarmDto> { //TODO: test code 삭제
        val testAlarmData = listOf(
            AlarmDto(
                qTitle = "test title 1_1",
                sqId = "test title 1_1",
                creator = "neonaver",
                date = Date(Calendar.getInstance().apply { set(2024, 8, 15) }.timeInMillis)
            )
        )
        return testAlarmData
    }
}
package com.sw.wordgarden.data.datasource.local

import com.sw.wordgarden.data.dto.AlarmDto

interface LocalDataSource {

    //user
    suspend fun saveUid(uid: String)
    suspend fun getUid(): String?
    suspend fun deleteUid()

    //quiz
    suspend fun saveDailyLimit(count: Int)
    suspend fun getDailyLimit(): Int?
    suspend fun deleteDailyLimit()
    suspend fun getAlarmList(): List<AlarmDto>?
}
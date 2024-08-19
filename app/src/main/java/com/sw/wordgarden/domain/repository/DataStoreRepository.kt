package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.alarm.AlarmEntity

interface DataStoreRepository {

    //user
    suspend fun saveUid(uid: String)
    suspend fun getUid(): String?
    suspend fun deleteUid()

    //quiz
    suspend fun saveDailyLimit(count: Int)
    suspend fun getDailyLimit(): Int?
    suspend fun deleteDailyLimit()

    //alarm
    suspend fun getAlarmList(): List<AlarmEntity>?
}
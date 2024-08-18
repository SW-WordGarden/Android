package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.local.LocalDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.AlarmEntity
import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): DataStoreRepository {

    //user
    override suspend fun saveUid(uid: String) {
        localDataSource.saveUid(uid)
    }

    override suspend fun getUid(): String? {
        return localDataSource.getUid()
    }

    override suspend fun deleteUid() {
        localDataSource.deleteUid()
    }

    //quiz
    override suspend fun saveDailyLimit(count: Int) {
        localDataSource.saveDailyLimit(count)
    }

    override suspend fun getDailyLimit(): Int? {
        return localDataSource.getDailyLimit()
    }

    override suspend fun deleteDailyLimit() {
        localDataSource.deleteDailyLimit()
    }

    override suspend fun getAlarmList(): List<AlarmEntity>? {
        return localDataSource.getAlarmList()?.map { it.toEntity() }
    }
}
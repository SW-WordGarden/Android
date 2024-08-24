package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.local.LocalDataSource
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

    override suspend fun getUidForService(): String? {
        return localDataSource.getUidForService()
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
}
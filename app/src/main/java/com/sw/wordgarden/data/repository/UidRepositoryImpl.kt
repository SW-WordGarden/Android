package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.local.LocalDataSource
import com.sw.wordgarden.domain.repository.UidRepository
import javax.inject.Inject

class UidRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): UidRepository {
    override suspend fun saveUid(uid: String) {
        localDataSource.saveUid(uid)
    }

    override suspend fun getUid(): String? {
        return localDataSource.getUid()
    }

    override suspend fun deleteUid() {
        localDataSource.deleteUid()
    }
}
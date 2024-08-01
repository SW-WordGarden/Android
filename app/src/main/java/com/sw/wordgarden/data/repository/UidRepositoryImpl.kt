package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.local.LocalDataAuth
import com.sw.wordgarden.domain.repository.UidRepository
import javax.inject.Inject

class UidRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataAuth
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
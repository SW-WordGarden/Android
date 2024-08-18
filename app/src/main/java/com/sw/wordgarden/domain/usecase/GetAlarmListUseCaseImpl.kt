package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.AlarmEntity
import com.sw.wordgarden.domain.repository.DataStoreRepository
import javax.inject.Inject

class GetAlarmListUseCaseImpl @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : GetAlarmListUseCase {
    override suspend fun invoke(): List<AlarmEntity>? {
        return dataStoreRepository.getAlarmList()
    }
}
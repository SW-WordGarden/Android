package com.sw.wordgarden.domain.usecase.alarm

import com.sw.wordgarden.domain.entity.alarm.ShareRequestEntity

interface MakeSharingAlarmUseCase {
    suspend operator fun invoke(shareRequest: ShareRequestEntity)
}
package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.ReportInfoEntity

interface ReportFriendUseCase {
    suspend operator fun invoke(reportInfo: ReportInfoEntity)
}
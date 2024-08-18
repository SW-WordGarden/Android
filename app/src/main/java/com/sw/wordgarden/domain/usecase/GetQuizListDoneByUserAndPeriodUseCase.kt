package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import java.util.Date

interface GetQuizListDoneByUserAndPeriodUseCase {
    suspend operator fun invoke(startDate: Date, endDate: Date): List<SelfQuizEntity>?
}
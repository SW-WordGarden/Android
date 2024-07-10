package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity
import java.util.Date

interface GetQuizeListDoneByUserAndPeriodUseCase {
    suspend operator fun invoke(startDate: Date, endDate: Date): List<QuizListEntity>?
}
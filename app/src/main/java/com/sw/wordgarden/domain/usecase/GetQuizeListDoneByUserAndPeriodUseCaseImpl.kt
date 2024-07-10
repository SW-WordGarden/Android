package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity
import java.util.Date

class GetQuizeListDoneByUserAndPeriodUseCaseImpl: GetQuizeListDoneByUserAndPeriodUseCase {
    override suspend fun invoke(startDate: Date, endDate: Date): List<QuizListEntity>? {
        TODO("Not yet implemented")
    }
}
package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import java.util.Date
import javax.inject.Inject

class GetQuizeListDoneByUserAndPeriodUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizeListDoneByUserAndPeriodUseCase {
    override suspend fun invoke(startDate: Date, endDate: Date): List<SelfQuizEntity>? {
        TODO("Not yet implemented")
    }
}
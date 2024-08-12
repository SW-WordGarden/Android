package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetQuizListMadeByUserByTitleUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetQuizListMadeByUserByTitleUseCase {
    override suspend fun invoke(title: String): QuizListEntity? {
        return quizRepository.getQuizListMadeByUserByTitle(title)
    }
}
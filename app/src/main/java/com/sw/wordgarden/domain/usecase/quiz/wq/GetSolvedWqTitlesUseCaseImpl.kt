package com.sw.wordgarden.domain.usecase.quiz.wq

import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetSolvedWqTitlesUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetSolvedWqTitlesUseCase {
    override suspend fun invoke(): Set<String>? {
        return quizRepository.getSolvedWqTitles()
    }
}
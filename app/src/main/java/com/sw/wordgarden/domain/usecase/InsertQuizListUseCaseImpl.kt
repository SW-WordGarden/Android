package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class InsertQuizListUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : InsertQuizListUseCase {
    override suspend fun invoke(quizList: SelfQuizEntity) {
        quizRepository.insertQuizList(quizList)
    }
}
package com.sw.wordgarden.domain.usecase.quiz.sq

import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetUserSqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetUserSqUseCase {
    override suspend fun invoke(creatorUid: String, sqId: String): SqEntity? {
        return quizRepository.getUserSq(creatorUid, sqId)
    }
}
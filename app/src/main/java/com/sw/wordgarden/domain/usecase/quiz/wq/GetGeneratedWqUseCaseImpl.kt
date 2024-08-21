package com.sw.wordgarden.domain.usecase.quiz.wq

import android.util.Log
import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class GetGeneratedWqUseCaseImpl @Inject constructor(
    private val quizRepository: QuizRepository
) : GetGeneratedWqUseCase {

    private val TAG = "ServerDataSourceImpl"

    override suspend fun invoke(): List<WqResponseEntity>? {
        val temp = quizRepository.getGeneratedWq()
        Log.d(TAG, "GetGeneratedWqUseCase : ${temp}")

        return temp
    }
}
package com.sw.wordgarden.data.repository

import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import java.util.Date

class QuizRepositoryImpl: QuizRepository {
    override suspend fun insertQuizList(quizList: QuizListEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteQuizList(quizListId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateQuizList(quizList: QuizListEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListByType(type: Boolean): List<QuizListEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListMadeByUser(): List<QuizListEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<QuizListEntity>? {
        TODO("Not yet implemented")
    }
}
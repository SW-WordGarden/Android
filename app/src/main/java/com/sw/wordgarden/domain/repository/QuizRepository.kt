package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.QuizListEntity
import java.util.Date

interface QuizRepository {
    suspend fun insertQuizList(quizList: QuizListEntity)
    suspend fun deleteQuizList(quizListId: String)
    suspend fun updateQuizList(quizList: QuizListEntity)
    suspend fun getQuizListByType(type: Boolean): List<QuizListEntity>?
    suspend fun getQuizListMadeByUser(): List<QuizListEntity>?
    suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<QuizListEntity>?
}
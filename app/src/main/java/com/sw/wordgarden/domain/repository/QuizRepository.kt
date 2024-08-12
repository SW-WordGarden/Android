package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.QuizListEntity
import java.util.Date

interface QuizRepository {
    suspend fun insertQuizList(quizList: QuizListEntity)
    suspend fun deleteQuizList(quizListId: String)
    suspend fun getQuizListByType(type: Boolean): QuizListEntity?
    suspend fun getQuizListAllType(): QuizListEntity?
    suspend fun getQuizListMadeByUser(): List<String>?
    suspend fun getQuizListMadeByUserByTitle(title: String): QuizListEntity?
    suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<QuizListEntity>?
    suspend fun getTodayQuiz(): QuizListEntity?
    suspend fun sendQuizAnswer(quizResult: QuizListEntity)
}
package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import java.util.Date

interface QuizRepository {
    suspend fun insertQuizList(quizList: SelfQuizEntity)
    suspend fun deleteQuizList(quizListId: String)
    suspend fun getQuizListByType(type: Boolean): SelfQuizEntity?
    suspend fun getQuizListAllType(): SelfQuizEntity?
    suspend fun getQuizListMadeByUser(): List<QuizSummaryEntity>?
    suspend fun getQuizListMadeByUserByQuizId(quizId: String): SelfQuizEntity?
    suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<SelfQuizEntity>?
    suspend fun getTodayQuiz(): SelfQuizEntity?
    suspend fun sendQuizAnswer(quizResult: SelfQuizEntity)
}
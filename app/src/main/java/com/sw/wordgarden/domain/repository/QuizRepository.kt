package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.SelfQuizCreatorInfoEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.entity.SolveQuizEntity
import java.util.Date

interface QuizRepository {
    suspend fun insertQuizList(selfQuiz: SelfQuizEntity)
    suspend fun deleteQuizList(quizId: String)
    suspend fun getQuizListByType(type: Boolean): SelfQuizEntity?
    suspend fun getQuizListAllType(): SelfQuizEntity?
    suspend fun getQuizListMadeByUser(): List<QuizSummaryEntity>?
    suspend fun getQuizListMadeByUserByQuizId(quizId: String): SelfQuizEntity?
    suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<SelfQuizEntity>?
    suspend fun getQuizBySelfQuizId(quizId: String): SelfQuizEntity?
    suspend fun getSolvedSelfQuizTitleList(): List<String>?
    suspend fun getSolvedSelfQuizResult(title: String): SelfQuizEntity?
    suspend fun getTodayQuiz(): SelfQuizEntity?
    suspend fun getSelfQuizCreatorInfo(quizId: String): SelfQuizCreatorInfoEntity?
    suspend fun sendQuizAnswer(quizResult: SelfQuizEntity)
    suspend fun submitSelfQuiz(solvedQuiz: SolveQuizEntity)
}
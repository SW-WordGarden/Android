package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.quiz.SqCreatorInfoEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.entity.quiz.SqSolveQuizEntity
import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.entity.quiz.WqStateEntity
import com.sw.wordgarden.domain.entity.quiz.WqSubmissionEntity
import com.sw.wordgarden.domain.entity.quiz.WqWrongAnswerEntity

interface QuizRepository {
    //wq
    suspend fun getGeneratedWq(): List<WqResponseEntity>?
    suspend fun submitWq(wqSubmission: WqSubmissionEntity)
    suspend fun getWqState(): WqStateEntity?
    suspend fun getWrongWqs(): List<WqWrongAnswerEntity>?
    suspend fun getSolvedWqTitles(): Set<String>?
    suspend fun getSolvedWq(title: String): List<WqResponseEntity>?

    //sq
    suspend fun createNewSq(selfQuiz: SqEntity)
    suspend fun getUserSqTitles(): List<QuizSummaryEntity>?
    suspend fun getUserSq(creatorUid: String, quizId: String): SqEntity?
    suspend fun getSq(quizId: String): List<SqQuestionAnswerEntity>?
    suspend fun submitSq(solvedQuiz: SqSolveQuizEntity)
    suspend fun getSolvedSqTitles(): List<QuizSummaryEntity>?
    suspend fun getSolvedSq(title: String): SqEntity?
    suspend fun getSqCreatorInfo(quizId: String): SqCreatorInfoEntity?
    suspend fun shareQuiz(quizTitle: String, friendId: String)
}
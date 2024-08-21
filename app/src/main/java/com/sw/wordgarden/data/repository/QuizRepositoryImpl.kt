package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuizSummaryEntity
import com.sw.wordgarden.domain.entity.quiz.SqCreatorInfoEntity
import com.sw.wordgarden.domain.entity.quiz.SqEntity
import com.sw.wordgarden.domain.entity.quiz.SqSolveQuizEntity
import com.sw.wordgarden.domain.entity.quiz.WqResponseEntity
import com.sw.wordgarden.domain.entity.quiz.WqStateEntity
import com.sw.wordgarden.domain.entity.quiz.WqSubmissionEntity
import com.sw.wordgarden.domain.entity.quiz.WqWrongAnswerEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : QuizRepository {

    //wq
    override suspend fun getGeneratedWq(): List<WqResponseEntity>? {
        return serverDataSource.getGeneratedWq()?.map { it.toEntity() }
    }

    override suspend fun submitWq(wqSubmission: WqSubmissionEntity) {
        serverDataSource.submitWq(wqSubmission.toDto())
    }

    override suspend fun getWqState(): WqStateEntity? {
        return serverDataSource.getWqState()?.toEntity()
    }

    override suspend fun getWrongWqs(): List<WqWrongAnswerEntity>? {
        return serverDataSource.getWrongWqs()?.map { it.toEntity() }
    }

    override suspend fun getSolvedWqTitles(): Set<String>? {
        return serverDataSource.getSolvedWqTitles()
    }

    override suspend fun getSolvedWq(title: String): List<WqResponseEntity>? {
        return serverDataSource.getSolvedWq(title)?.map { it.toEntity() }
    }

    //sq
    override suspend fun createNewSq(selfQuiz: SqEntity) {
        serverDataSource.createNewSq(selfQuiz.toDto())
    }

    override suspend fun getUserSqTitles(): List<SqQuizSummaryEntity>? {
        return serverDataSource.getUserSqTitles()?.map { it.toEntity() }
    }

    override suspend fun getUserSq(creatorUid: String, quizId: String): SqEntity? {
        return serverDataSource.getUserSq(creatorUid, quizId)?.toEntity()
    }

    override suspend fun getSq(quizId: String): List<SqQuestionAnswerEntity>? {
        return serverDataSource.getSq(quizId)?.map { it.toEntity() }
    }

    override suspend fun submitSq(solvedQuiz: SqSolveQuizEntity) {
        return serverDataSource.submitSq(solvedQuiz.toDto())
    }

    override suspend fun getSolvedSqTitles(): List<String>? {
        return serverDataSource.getSolvedSqTitles()
    }

    override suspend fun getSolvedSq(title: String): SqEntity? {
        return serverDataSource.getSolvedSq(title)?.toEntity()
    }

    override suspend fun getSqCreatorInfo(quizId: String): SqCreatorInfoEntity? {
        return serverDataSource.getSqCreatorInfo(quizId)?.toEntity()
    }

    override suspend fun shareQuiz(quizTitle: String, friendId: String) {
        return serverDataSource.shareQuiz(quizTitle, friendId)
    }

}
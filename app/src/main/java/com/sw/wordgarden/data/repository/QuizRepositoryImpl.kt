package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.quiz.OneQuizEntity
import com.sw.wordgarden.domain.entity.quiz.SqQuestionAnswerEntity
import com.sw.wordgarden.domain.entity.quiz.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.quiz.SqCreatedInfoEntity
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

    override suspend fun getWqOrSolvedWq(title: String, isSolved: Boolean): List<WqResponseEntity>? {
        return serverDataSource.getWqOrSolvedWq(title, isSolved)?.map { it.toEntity() }
    }

    //sq
    override suspend fun createNewSq(selfQuiz: SqEntity): SqCreatedInfoEntity? {
        return serverDataSource.createNewSq(selfQuiz.toDto())?.toEntity()
    }

    override suspend fun getUserSqTitles(): List<QuizSummaryEntity>? {
        return serverDataSource.getUserSqTitles()?.map { it.toEntity() }
    }

    override suspend fun getUserSq(creatorUid: String?, quizId: String): SqEntity? {
        return serverDataSource.getUserSq(creatorUid, quizId)?.toEntity()
    }

    override suspend fun getSq(quizId: String): List<SqQuestionAnswerEntity>? {
        return serverDataSource.getSq(quizId)?.map { it.toEntity() }
    }

    override suspend fun submitSq(solvedQuiz: SqSolveQuizEntity) {
        return serverDataSource.submitSq(solvedQuiz.toDto())
    }

    override suspend fun getSolvedSqTitles(): List<QuizSummaryEntity>? {
        return serverDataSource.getSolvedSqTitles()?.map { it.toEntity() }
    }

    override suspend fun getSolvedSq(title: String): SqEntity? {
        return serverDataSource.getSolvedSq(title)?.toEntity()
    }

    override suspend fun getSqCreatorInfo(quizId: String): SqCreatorInfoEntity? {
        return serverDataSource.getSqCreatorInfo(quizId)?.toEntity()
    }

    override suspend fun getLockQuiz(): OneQuizEntity? {
        return serverDataSource.getLockQuiz()?.toEntity()
    }
}
package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.SelfQuizCreatorInfoEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.entity.SolveQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import java.util.Date
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : QuizRepository {
    override suspend fun insertQuizList(selfQuiz: SelfQuizEntity) {
        serverDataSource.insertQuizList(selfQuiz.toDto())
    }

    override suspend fun deleteQuizList(quizId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListByType(type: Boolean): SelfQuizEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListAllType(): SelfQuizEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListMadeByUser(): List<QuizSummaryEntity>? =
        serverDataSource.getQuizListMadeByUser()?.map { it.toEntity() }

    override suspend fun getQuizListMadeByUserByQuizId(quizId: String): SelfQuizEntity? {
        return serverDataSource.getQuizListMadeByUserByQuizId(quizId)?.toEntity()
    }

    override suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<SelfQuizEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizBySelfQuizId(quizId: String): SelfQuizEntity? {
        return serverDataSource.getQuizBySelfQuizId(quizId)?.toEntity()
    }

    override suspend fun getSolvedSelfQuizTitleList(): List<String>? {
        return serverDataSource.getSolvedSelfQuizTitleList()
    }

    override suspend fun getSolvedSelfQuizResult(title: String): SelfQuizEntity? {
        return serverDataSource.getSolvedSelfQuizResult(title)?.toEntity()
    }

    override suspend fun getTodayQuiz(): SelfQuizEntity? {
        return serverDataSource.getTodayQuiz()?.toEntity()
    }

    override suspend fun getSelfQuizCreatorInfo(quizId: String): SelfQuizCreatorInfoEntity? {
        return serverDataSource.getSelfQuizCreatorInfo(quizId)?.toEntity()
    }

    override suspend fun sendQuizAnswer(quizResult: SelfQuizEntity) {
        serverDataSource.sendQuizAnswer(quizResult.toDto())
    }

    override suspend fun submitSelfQuiz(solvedQuiz: SolveQuizEntity) {
        serverDataSource.submitSelfQuiz(solvedQuiz.toDto())
    }
}
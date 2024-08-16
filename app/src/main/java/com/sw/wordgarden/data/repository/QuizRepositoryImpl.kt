package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.QuizSummaryEntity
import com.sw.wordgarden.domain.entity.SelfQuizEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import java.util.Date
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : QuizRepository {
    override suspend fun insertQuizList(quizList: SelfQuizEntity) {
        serverDataSource.insertQuizList(quizList.toDto())
    }

    override suspend fun deleteQuizList(quizListId: String) {
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

    override suspend fun getTodayQuiz(): SelfQuizEntity? {
        return serverDataSource.getTodayQuiz()?.toEntity()
    }

    override suspend fun sendQuizAnswer(quizResult: SelfQuizEntity) {
        serverDataSource.sendQuizAnswer(quizResult.toDto())
    }
}
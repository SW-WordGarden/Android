package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.mapper.ServerMapper.toDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.QuizListEntity
import com.sw.wordgarden.domain.repository.QuizRepository
import java.util.Date
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : QuizRepository {
    override suspend fun insertQuizList(quizList: QuizListEntity) {
        serverDataSource.insertQuizList(quizList.toDto())
    }

    override suspend fun deleteQuizList(quizListId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListByType(type: Boolean): QuizListEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListAllType(): QuizListEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun getQuizListMadeByUser(): List<String>? {
        return serverDataSource.getQuizListMadeByUser()
    }

    override suspend fun getQuizListMadeByUserByTitle(title: String): QuizListEntity? {
        return serverDataSource.getQuizListMadeByUserByTitle(title)?.toEntity()
    }

    override suspend fun getQuizListDoneByUserAndPeriod(startDate: Date, endDate: Date): List<QuizListEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun getTodayQuiz(): QuizListEntity? {
        return serverDataSource.getTodayQuiz()?.toEntity()
    }

    override suspend fun sendQuizAnswer(quizResult: QuizListEntity) {
        serverDataSource.sendQuizAnswer(quizResult.toDto())
    }
}
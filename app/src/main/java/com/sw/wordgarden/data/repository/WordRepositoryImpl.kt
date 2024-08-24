package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.dto.WordDto
import com.sw.wordgarden.data.mapper.ServerMapper.toEntity
import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : WordRepository {
    override suspend fun getWeeklyCategoryWordList(category: String): List<WordEntity>? {
        return serverDataSource.getWeeklyCategoryWordList(category)?.map { it.toEntity() }
    }

    override suspend fun getDetailWord(wordId: String): WordEntity? {
         return serverDataSource.getDetailWord(wordId)?.toEntity()
    }

    override suspend fun insertLikedWord(wordId: String, isLiked: Boolean) {
        serverDataSource.insertLikedWord(wordId, isLiked)
    }

    override suspend fun getWordLikedStatus(wordId: String): Boolean? {
        return serverDataSource.getWordLikedStatus(wordId)
    }

    override suspend fun getWeeklyWordList(): List<WordEntity>? {
        return serverDataSource.getWeeklyWordList()?.map { it.toEntity() }
    }

    override suspend fun getRandomWord(): WordEntity? {
        return serverDataSource.getRandomWord()?.toEntity()
    }

}
package com.sw.wordgarden.data.repository

import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val serverDataSource: ServerDataSource
) : WordRepository {
    override suspend fun insertLikedWord(word: WordEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteLikedWord(wordId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getLikedWordList(): List<WordEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun getWeeklyWordList(): List<WordEntity>? {
        TODO("Not yet implemented")
    }

}
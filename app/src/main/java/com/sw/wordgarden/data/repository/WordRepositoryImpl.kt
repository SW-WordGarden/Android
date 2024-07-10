package com.sw.wordgarden.data.repository

import com.sw.wordgarden.domain.entity.WordEntity
import com.sw.wordgarden.domain.repository.WordRepository

class WordRepositoryImpl: WordRepository {
    override suspend fun getWordList(): List<WordEntity>? {
        TODO("Not yet implemented")
    }
}
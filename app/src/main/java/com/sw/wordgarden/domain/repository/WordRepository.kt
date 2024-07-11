package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.WordEntity

interface WordRepository {
    suspend fun insertLikedWord(word: WordEntity)
    suspend fun deleteLikedWord(wordId: String)
    suspend fun getLikedWordList(): List<WordEntity>?
    suspend fun getWeeklyWordList(): List<WordEntity>?
}
package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.WordEntity

interface WordRepository {
    suspend fun getWeeklyCategoryWordList(category:String) : List<WordEntity>?
    suspend fun getDetailWord(wordId: String): WordEntity?
    suspend fun insertLikedWord(wordId: String, isLiked: Boolean)
    suspend fun getWordLikedStatus(wordId: String) :Boolean?
    suspend fun getWeeklyWordList() : List<WordEntity>?
    suspend fun getRandomWord() : WordEntity?
}
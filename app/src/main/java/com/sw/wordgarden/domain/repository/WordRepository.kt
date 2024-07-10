package com.sw.wordgarden.domain.repository

import com.sw.wordgarden.domain.entity.WordEntity

interface WordRepository {
    suspend fun getWordList(): List<WordEntity>?
}
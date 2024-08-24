package com.sw.wordgarden.domain.usecase.word

interface InsertLikedWordUseCase {
    suspend operator fun invoke(wordId:String, isLiked:Boolean)
}
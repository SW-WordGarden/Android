package com.sw.wordgarden.domain.usecase.word

interface GetWordLikedStatusUseCase {
    suspend operator fun invoke(wordId:String) : Boolean?
}
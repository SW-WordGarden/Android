package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.entity.WordEntity

interface GetDetailWordUseCase {
    suspend operator fun invoke(wordId:String):WordEntity?
}
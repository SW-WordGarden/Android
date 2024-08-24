package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.entity.WordEntity

interface GetWeeklyCategoryWordListUseCase {
    suspend operator fun invoke(category:String) : List<WordEntity>?
}
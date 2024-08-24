package com.sw.wordgarden.domain.usecase.word

import com.sw.wordgarden.domain.entity.WordEntity

interface GetRandomWordUseCase {
    suspend operator fun invoke() : WordEntity?
}
package com.sw.wordgarden.domain.usecase.garden

import com.sw.wordgarden.domain.entity.TreeEntity

interface GetGrowInfoUseCase {
    suspend operator fun invoke():TreeEntity?
}
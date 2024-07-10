package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.TreeEntity

interface GetTreeListUseCase {
    suspend operator fun invoke(): List<TreeEntity>?
}
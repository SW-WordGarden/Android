package com.sw.wordgarden.domain.usecase.garden

import com.sw.wordgarden.domain.entity.user.UserResponseEntity

interface GetUserResourceUseCase {
    suspend operator fun invoke():UserResponseEntity?
}
package com.sw.wordgarden.domain.usecase.user

import com.sw.wordgarden.domain.entity.user.LoginRequestEntity

interface InsertUserUseCase {
    suspend operator fun invoke(loginRequestEntity: LoginRequestEntity)
}
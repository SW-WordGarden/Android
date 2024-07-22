package com.sw.wordgarden.domain.usecase

import com.sw.wordgarden.domain.entity.SignUpEntity
import com.sw.wordgarden.domain.entity.UserEntity

interface InsertUserUseCase {
    suspend operator fun invoke(signUpEntity: SignUpEntity)
}
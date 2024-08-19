package com.sw.wordgarden.domain.usecase.user

interface UpdateUserNicknameUseCase {
    suspend operator fun invoke(nickname: String)
}
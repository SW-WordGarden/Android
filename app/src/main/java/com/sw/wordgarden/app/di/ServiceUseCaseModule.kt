package com.sw.wordgarden.app.di

import com.sw.wordgarden.domain.usecase.user.SendFirebaseTokenUseCase
import com.sw.wordgarden.domain.usecase.user.SendFirebaseTokenUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.scopes.ServiceScoped


@Module
@InstallIn(ServiceComponent::class)
abstract class ServiceUseCaseModule {
    @Binds
    @ServiceScoped
    abstract fun bindSendFirebaseTokenUseCase(
        sendFirebaseTokenUseCaseImpl: SendFirebaseTokenUseCaseImpl
    ): SendFirebaseTokenUseCase
}
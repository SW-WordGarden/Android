package com.sw.wordgarden.app.di

import com.sw.wordgarden.domain.usecase.datastore.GetUidForServiceUseCase
import com.sw.wordgarden.domain.usecase.datastore.GetUidForServiceUseCaseImpl
import com.sw.wordgarden.domain.usecase.user.UpdateFcmTokenUseCase
import com.sw.wordgarden.domain.usecase.user.UpdateFcmTokenUseCaseImpl
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
    abstract fun bindUpdateFcmTokenUseCase(
        updateFcmTokenUseCaseImpl: UpdateFcmTokenUseCaseImpl
    ): UpdateFcmTokenUseCase

    @Binds
    @ServiceScoped
    abstract fun bindGetUidForServiceUseCase(
        getUidForServiceUseCaseImpl: GetUidForServiceUseCaseImpl
    ): GetUidForServiceUseCase
}
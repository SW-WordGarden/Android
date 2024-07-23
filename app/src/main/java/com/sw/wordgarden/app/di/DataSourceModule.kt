package com.sw.wordgarden.app.di

import com.sw.wordgarden.data.datasource.local.LocalDataAuth
import com.sw.wordgarden.data.datasource.local.LocalDataAuthImpl
import com.sw.wordgarden.data.datasource.remote.ServerDataSource
import com.sw.wordgarden.data.datasource.remote.ServerDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindSererDataSource(
        serverDataSourceImpl: ServerDataSourceImpl
    ): ServerDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataAuthImpl
    ): LocalDataAuth
}
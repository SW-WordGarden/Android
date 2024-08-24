package com.sw.wordgarden.app.di

import com.sw.wordgarden.data.datasource.remote.retrofit.RetrofitClient
import com.sw.wordgarden.data.datasource.remote.retrofit.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return RetrofitClient.instance
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }
}
package com.sw.wordgarden.app.di

import com.sw.wordgarden.data.repository.AlarmRepositoryImpl
import com.sw.wordgarden.data.repository.GardenRepositoryImpl
import com.sw.wordgarden.data.repository.QuizRepositoryImpl
import com.sw.wordgarden.data.repository.DataStoreRepositoryImpl
import com.sw.wordgarden.data.repository.UserRepositoryImpl
import com.sw.wordgarden.data.repository.WordRepositoryImpl
import com.sw.wordgarden.domain.repository.AlarmRepository
import com.sw.wordgarden.domain.repository.GardenRepository
import com.sw.wordgarden.domain.repository.QuizRepository
import com.sw.wordgarden.domain.repository.UserRepository
import com.sw.wordgarden.domain.repository.WordRepository
import com.sw.wordgarden.domain.repository.DataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindGardenRepopsitory(
        gardenRepositoryImpl: GardenRepositoryImpl
    ): GardenRepository

    @Binds
    @Singleton
    abstract fun bindQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ): QuizRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindWordRepository(
        wordRepositoryImpl: WordRepositoryImpl
    ): WordRepository

    @Binds
    @Singleton
    abstract fun bindDataStoreRepository(
        dataStoreRepositoryImpl: DataStoreRepositoryImpl
    ): DataStoreRepository

    @Binds
    @Singleton
    abstract fun bindAlarmRepository(
        alarmRepositoryImpl: AlarmRepositoryImpl
    ): AlarmRepository
}
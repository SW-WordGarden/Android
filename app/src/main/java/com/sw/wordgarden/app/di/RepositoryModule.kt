package com.sw.wordgarden.app.di

import com.sw.wordgarden.data.repository.FriendRepositoryImpl
import com.sw.wordgarden.data.repository.GardenRepositoryImpl
import com.sw.wordgarden.data.repository.QuizRepositoryImpl
import com.sw.wordgarden.data.repository.UserRepositoryImpl
import com.sw.wordgarden.data.repository.WordRepositoryImpl
import com.sw.wordgarden.domain.repository.FriendRepository
import com.sw.wordgarden.domain.repository.GardenRepository
import com.sw.wordgarden.domain.repository.QuizRepository
import com.sw.wordgarden.domain.repository.UserRepository
import com.sw.wordgarden.domain.repository.WordRepository
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
    abstract fun bindFriendRepopsitory(
        friendRepositoryImpl: FriendRepositoryImpl
    ): FriendRepository

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
    abstract fun bindWordRepositoryy(
        wordRepositoryImpl: WordRepositoryImpl
    ): WordRepository
}
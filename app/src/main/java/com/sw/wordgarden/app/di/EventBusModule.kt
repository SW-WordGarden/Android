package com.sw.wordgarden.app.di

import com.sw.wordgarden.domain.bus.ItemChangedEventBus
import com.sw.wordgarden.presentation.shared.ItemChangedEventBusImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EventBusModule {
    @Binds
    @Singleton
    abstract fun bindsItemChangedEventBus(
        itemChangedEventBusImpl: ItemChangedEventBusImpl
    ): ItemChangedEventBus
}
package com.andrei.hearthstoneassesment.di

import com.andrei.hearthstoneassesment.network.ApiService
import com.andrei.hearthstoneassesment.network.model.HearthstoneCardDtoMapper
import com.andrei.hearthstoneassesment.network.model.HearthstoneCardPaginatedListMapper
import com.andrei.hearthstoneassesment.presentation.MyApplication
import com.andrei.hearthstoneassesment.repository.HearthstoneCardsRepository
import com.andrei.hearthstoneassesment.repository.HearthstoneCardsRepositoryImpl
import com.andrei.hearthstoneassesment.repository.MockHearthstoneCardsRepository
import com.andrei.hearthstoneassesment.repository.MockHearthstoneCardsRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHearthstoneCardsRepository(
        apiService: ApiService,
        mapper: HearthstoneCardDtoMapper
    ): HearthstoneCardsRepository {
        return HearthstoneCardsRepositoryImpl(apiService, mapper)
    }

    @Singleton
    @Provides
    fun provideMockHearthstoneCardsRepository(
        gson: Gson,
        application: MyApplication,
        mapper: HearthstoneCardPaginatedListMapper
    ): MockHearthstoneCardsRepository {
        return MockHearthstoneCardsRepositoryImpl(gson, application, mapper)
    }
}

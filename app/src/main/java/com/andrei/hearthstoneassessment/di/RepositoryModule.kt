package com.andrei.hearthstoneassessment.di

import com.andrei.hearthstoneassessment.network.ApiService
import com.andrei.hearthstoneassessment.network.model.HearthstoneCardDtoMapper
import com.andrei.hearthstoneassessment.network.model.HearthstoneCardPaginatedListMapper
import com.andrei.hearthstoneassessment.presentation.AbstractApplication
import com.andrei.hearthstoneassessment.presentation.MyApplication
import com.andrei.hearthstoneassessment.repository.HearthstoneCardsRepository
import com.andrei.hearthstoneassessment.repository.HearthstoneCardsRepositoryImpl
import com.andrei.hearthstoneassessment.repository.MockHearthstoneCardsRepository
import com.andrei.hearthstoneassessment.repository.MockHearthstoneCardsRepositoryImpl
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
        application: AbstractApplication,
        mapper: HearthstoneCardPaginatedListMapper
    ): MockHearthstoneCardsRepository {
        return MockHearthstoneCardsRepositoryImpl(gson, application, mapper)
    }
}

package com.andrei.hearthstoneassessment.di

import com.andrei.hearthstoneassessment.BuildConfig
import com.andrei.hearthstoneassessment.network.ApiService
import com.andrei.hearthstoneassessment.network.model.HearthstoneCardDtoMapper
import com.andrei.hearthstoneassessment.network.model.HearthstoneCardPaginatedListMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHearthstoneCardMapper(): HearthstoneCardDtoMapper {
        return HearthstoneCardDtoMapper()
    }

    @Singleton
    @Provides
    fun provideHearthstoneCardPaginatedListMapper(): HearthstoneCardPaginatedListMapper {
        return HearthstoneCardPaginatedListMapper()
    }

    @Singleton
    @Provides
    fun gsonAdapter(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideApiService(gson: Gson): ApiService {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        else loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.url_api)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build().create(ApiService::class.java)
    }
}
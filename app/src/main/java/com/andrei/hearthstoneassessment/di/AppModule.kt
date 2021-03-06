package com.andrei.hearthstoneassessment.di

import android.content.Context
import com.andrei.hearthstoneassessment.presentation.AbstractApplication
import com.andrei.hearthstoneassessment.presentation.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): AbstractApplication {
        return app as AbstractApplication
    }
}
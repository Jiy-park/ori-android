package com.dd2d.data.source.remote.api

import com.dd2d.core.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class APIModule {
    companion object {
        @Provides
        @Singleton
        fun provideHttpClient(): HttpClient = NetworkModule.client
    }
}
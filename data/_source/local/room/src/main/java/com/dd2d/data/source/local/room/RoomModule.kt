package com.dd2d.data.source.local.room

import android.content.Context
import com.dd2d.data.source.local.room.dao.SampleDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getDatabases(context)

    @Provides
    @Singleton
    fun provideSampleDAO(appDatabase: AppDatabase): SampleDAO = appDatabase.sampleDAO()
}
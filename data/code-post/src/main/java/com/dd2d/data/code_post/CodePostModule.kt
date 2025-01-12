package com.dd2d.data.code_post

import com.dd2d.data.code_post.repository.CodePostRepositoryImpl
import com.dd2d.domain.code_post.repository.CodePostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CodePostModule {
    @Binds
    @Singleton
    abstract fun bindsCodePostRepository(impl: CodePostRepositoryImpl): CodePostRepository
}
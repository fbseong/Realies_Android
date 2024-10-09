package com.selfpro.realies.data.network.module

import com.selfpro.network.repositoryimpl.NewsAPIRepositoryImpl
import com.selfpro.realies.data.network.api.NewsAPI
import com.selfpro.realies.data.network.repository.NewsAPIRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NewsAPIRepositoryModule {

    @Binds
    fun bindsNewsAPIRepository(
        newsAPIRepository: NewsAPIRepositoryImpl
    ): NewsAPIRepository
}
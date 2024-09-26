package com.selfpro.realies.data.network.module

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
object NewsAPIRepositoryModule {
    @Provides
    @Singleton
    fun provideNewsRepository(newsAPI: NewsAPI): NewsAPIRepository {
        return NewsAPIRepository(newsAPI)
    }
}
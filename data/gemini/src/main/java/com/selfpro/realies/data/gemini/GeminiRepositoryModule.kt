package com.selfpro.realies.data.gemini

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface GeminiRepositoryModule {
    @Binds
    fun bindsGeminiRepository(
        geminiRepository: GeminiRepositoryImpl
    ): GeminiRepository
}
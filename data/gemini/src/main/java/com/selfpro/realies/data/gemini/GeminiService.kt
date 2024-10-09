package com.selfpro.realies.data.gemini

import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


class GeminiService @Inject constructor() {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey
    )

    suspend fun generateNewsTitle(prompt: String): String {
        val response = generativeModel.generateContent("다음 뉴스에 쓰일 수 있는 제목을 1개만 만들어줘 $prompt")
        return response.text ?: ""
    }
}
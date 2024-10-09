package com.selfpro.realies.data.gemini

import javax.inject.Inject

class GeminiRepositoryImpl @Inject constructor(
private val geminiService: GeminiService
):GeminiRepository{
    override suspend fun generateNewsTitle(prompt: String): String {
        return geminiService.generateNewsTitle(prompt)
    }
}
package com.selfpro.realies.data.gemini

interface GeminiRepository {
    suspend fun generateNewsTitle(prompt: String): String
}
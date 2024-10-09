package com.selfpro.selfpro.data.domain

import com.selfpro.realies.data.gemini.GeminiRepository
import com.selfpro.realies.data.network.repository.NewsAPIRepository
import javax.inject.Inject

class GenerateNewsTitleUseCase @Inject constructor(
    private val repository: GeminiRepository
) {
    suspend operator fun invoke(prompt: String) = kotlin.runCatching {
        repository.generateNewsTitle(prompt)
    }
}
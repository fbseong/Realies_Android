package com.selfpro.selfpro.data.domain

import com.selfpro.realies.data.network.repository.NewsAPIRepository
import javax.inject.Inject

class GetRecommendationRealiesUseCase @Inject constructor(
    private val repository: NewsAPIRepository
) {
    suspend operator fun invoke(page: Int) = kotlin.runCatching {
        repository.getNewsRecommendation(page)
    }
}
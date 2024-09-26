package com.selfpro.realies.data.network.repository

import com.selfpro.realies.data.network.api.NewsAPI
import com.selfpro.realies.data.network.request.RealiesRequest
import javax.inject.Inject

class NewsAPIRepository @Inject constructor(
    private val newsAPI: NewsAPI
) {
    suspend fun getNewsRecommendation(page: Int): List<RealiesRequest>{
        return newsAPI.getNewsRecommendation(page)
    }
}

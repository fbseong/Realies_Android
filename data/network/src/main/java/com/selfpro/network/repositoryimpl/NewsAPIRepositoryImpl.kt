package com.selfpro.network.repositoryimpl

import com.selfpro.network.request.RealiesRequest
import com.selfpro.realies.data.network.api.NewsAPI
import com.selfpro.realies.data.network.repository.NewsAPIRepository
import javax.inject.Inject

internal class NewsAPIRepositoryImpl @Inject constructor(
    private val newsAPI: NewsAPI
) : NewsAPIRepository {
    override suspend fun getNewsRecommendation(page: Int): List<RealiesRequest> {
        return newsAPI.getNewsRecommendation(page)
    }
}
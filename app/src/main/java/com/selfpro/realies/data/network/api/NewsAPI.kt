package com.selfpro.realies.data.network.api

import com.selfpro.realies.data.network.request.RealiesRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/realies/recommendation")
    suspend fun getNewsRecommendation(
        @Query("page") page: Int
    ): List<RealiesRequest>

    @GET("/realies/provider")
    suspend fun getNewsProvider(
        @Query("provider") provider: String
    ): List<RealiesRequest>
}
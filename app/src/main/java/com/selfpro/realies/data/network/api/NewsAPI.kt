package com.selfpro.realies.data.network.api

import com.selfpro.realies.data.network.request.RealiesDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("/realies/recommendation")
    suspend fun getNewsRecommendation(
        @Query("page") page: Int
    ): List<RealiesDTO>
}
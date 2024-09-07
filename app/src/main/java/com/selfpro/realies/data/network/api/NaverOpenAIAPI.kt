package com.selfpro.realies.data.network.api

import com.selfpro.realies.data.network.request.NaverSummarizeRequest
import com.selfpro.realies.data.network.response.NaverSummarizeResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NaverOpenAIAPI {
    @POST("/text-summary/v1/summarize")
    suspend fun getSummarizedTitle(
        @Header("X-NCP-APIGW-API-KEY-ID") apiKeyId: String = "p3mb4j7znj",
        @Header("X-NCP-APIGW-API-KEY") apiKey: String = "W6QfzbUk3G0KewBY3mPaeyMWvognN25wu08XMEC6",
        @Header("Content-Type") contentType: String = "application/json",
        @Body naverSummarizeData: NaverSummarizeRequest
    ): NaverSummarizeResponse
}
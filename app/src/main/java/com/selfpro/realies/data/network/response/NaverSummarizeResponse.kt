package com.selfpro.realies.data.network.response

import kotlinx.serialization.Serializable

@Serializable
data class NaverSummarizeResponse(
    val summary: String
)
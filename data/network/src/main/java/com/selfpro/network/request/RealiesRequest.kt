package com.selfpro.network.request

data class RealiesRequest(
    val title: String,
    val content: String,
    val publishedAt: String,
    val images: List<String>?,
    val provider: String? = null,
    val providerImage: String? = null,
    val url: String? = null,
    val author: String? = null,
    val challengeRank: Int? = null
)
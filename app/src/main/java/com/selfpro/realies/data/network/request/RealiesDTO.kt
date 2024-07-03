package com.selfpro.realies.data.network.request

data class RealiesDTO(
    val title: String,
    val content: String,
    val publishedAt: String,
    val images: List<String>?,
    val provider: String? = null,
    val url: String? = null,
    val author: String? = null,
    val challengeRank: Int? = null
)
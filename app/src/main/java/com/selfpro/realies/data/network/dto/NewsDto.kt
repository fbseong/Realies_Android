package com.selfpro.realies.data.network.dto

data class NewsDto(
    val author: String? = null,
    val title: String,
    val image: String? = null,
    val url: String? = null,
    val broadCaster: String? = null,
    val content: String? = null,
    val publishedAt: String? = null
)
package com.selfpro.realies.data.network.request

import kotlinx.serialization.Serializable


@Serializable
data class NaverSummarizeRequest(
    val document: Document,
    val option: Option
) {

    @Serializable
    data class Document(
        val title: String,
        val content: String
    )

    @Serializable
    data class Option(
        val language: String,
        val model: String,
        val tone: Int,
        val summaryCount: Int
    )
}
package com.selfpro.realies.data.model

data class NewsThumbModel(
    val title: String,
    val content: String,
    val publishedAt: String,
    val images: List<String>,
    val provider: String?,
    val url: String?,
    val author: String?,
    val challengeRank: Int?,
    val subThumb: List<ThumbSubModel>
) {
    data class ThumbSubModel(
        val title: String,
        val createdAt: String
    )

    fun getThumbImageUrl(): String {
//        return "${App.baseUrl}/surveys/image/get?image=${image}"
        return ""
    }

    fun getBroadCasterImageUrl(): String {
        return ""
    }
}
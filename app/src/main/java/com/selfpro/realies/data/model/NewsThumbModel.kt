package com.selfpro.realies.data.model

data class NewsThumbModel(
    val title: String,
    val thumbImage: String,
    val createdAt: String,
    val broadCasterImage: String,
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
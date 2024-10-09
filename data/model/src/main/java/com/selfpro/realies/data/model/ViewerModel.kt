package com.selfpro.realies.data.model

data class ViewerModel(
    val newsType: String,
    val newsUrl: String,
) {
    object Type {
        val Realies = "Realies"
        val Major = "Major"
    }
}


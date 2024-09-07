package com.selfpro.realies.data.model

data class NewsAddMenuModel (
    val title: String,
    val description: String,
    val image: Int,
    val onClick: () -> Unit
)
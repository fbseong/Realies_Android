package com.selfpro.realies.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NewsAddMenuModel (
    val title: String,
    val description: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)
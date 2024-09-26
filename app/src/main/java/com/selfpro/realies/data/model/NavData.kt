package com.selfpro.realies.data.model

import androidx.compose.ui.graphics.painter.Painter

data class NavData (
    val text: String? = null,
    val icon: Painter,
    val iconSelected: Painter? = null,
    val onClick: () -> Unit = {},
)
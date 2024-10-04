package com.selfpro.realies.data.model

import androidx.annotation.DrawableRes

data class NavData(
    val id: String,
    val text: String? = null,
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int? = null,
    val onClick: () -> Unit = {},
)
package com.selfpro.realies.ui.color

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@JvmInline
value class SpColor(val value: ULong) {
    companion object {

        @Stable
        val StrokeGray = Color(0xFF6F7680)

        @Stable
        val BoxGray = Color(0xFFF3F5F7)

        @Stable
        val Black = Color(0xFF000000)

        @Stable
        val White = Color(0xFFFFFFFF)
    }
}
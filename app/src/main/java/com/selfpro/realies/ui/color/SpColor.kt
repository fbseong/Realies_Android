package com.selfpro.realies.ui.color

import android.content.res.Resources.Theme
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

        @Stable
        val Transparent = Color(0x00000000)

        @Stable
        val Error = Color(0xFFFF0000)

        @Stable
        val Theme = Color(0xFFFF9C54)

        @Stable
        val ThemeStart = Color(0xFFFFB054)

        @Stable
        val ThemeEnd = Color(0xFFFF6EA2)

        @Stable
        val Unable = Color(0xFFDFE0E2)

        @Stable
        val Void = Color(0x00000000)
    }
}
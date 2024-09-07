package com.selfpro.realies.util

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.selfpro.realies.ui.color.SpColor

@Composable
fun shimmerLoading(loading: Boolean = true, targetValue: Float = 1000f): Brush {
    return if (loading) {
        // Colors for the shimmer effect
        val shimmerColors = listOf(
            SpColor.ThemeStart.copy(alpha = 0.3f),
            SpColor.ThemeEnd.copy(alpha = 0.3f),
            SpColor.ThemeStart.copy(alpha = 0.3f),
        )

        // Start the animation transition
        val transition = rememberInfiniteTransition(label = "")
        val translateAnimation = transition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "",
        )

        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = translateAnimation.value, y = translateAnimation.value),
        )
    } else {
        Brush.linearGradient(listOf(SpColor.BoxGray, SpColor.BoxGray))
    }
}

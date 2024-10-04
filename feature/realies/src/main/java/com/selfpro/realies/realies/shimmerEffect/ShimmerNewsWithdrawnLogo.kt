package com.selfpro.realies.util.shimmerEffect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerNewsWithdrawnLogo() {
    Box(
        modifier = Modifier
            .padding(bottom = 1.dp)
            .background(
                brush = shimmerEffect(),
                shape = RoundedCornerShape(5.dp)
            )
            .height(14.dp)
            .width(100.dp)
    )
}

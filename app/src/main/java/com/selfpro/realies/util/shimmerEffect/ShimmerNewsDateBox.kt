package com.selfpro.realies.util.shimmerEffect

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShimmerNewsDateBox() {
    Box(
        modifier = Modifier.background(
            brush = shimmerEffect(),
            shape = RoundedCornerShape(5.dp)
        )
    ) {
        Text(
            text = "",
            fontSize = 12.sp,
            textAlign = TextAlign.Left,
            color = Color.Gray
        )
    }
}
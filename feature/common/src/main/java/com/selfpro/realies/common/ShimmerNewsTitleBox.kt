package com.selfpro.realies.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShimmerNewsTitleBox() {
    Box(
        modifier = Modifier
            .padding(bottom = 1.dp)
            .background(
                brush = shimmerEffect(),
                shape = RoundedCornerShape(5.dp)
            )
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "",
            fontSize = 15.sp,
            textAlign = TextAlign.Left,
            color = Color.Black
        )
    }
}
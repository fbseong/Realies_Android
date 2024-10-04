package com.selfpro.realies.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.selfpro.realies.util.common.SpColor

@Composable
fun RealiesSearchBox(onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .height(44.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            )
            .border(
                color = SpColor.StrokeGray,
                shape = RoundedCornerShape(100.dp),
                width = 0.5.dp
            )
            .background(
                color = SpColor.BoxGray,
                shape = RoundedCornerShape(100.dp)
            )
    ) {
        Text(
            text = "검색..",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp)
                .align(Alignment.Center),
            color = SpColor.StrokeGray
        )
    }
}
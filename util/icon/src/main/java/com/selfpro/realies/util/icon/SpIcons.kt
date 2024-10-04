package com.selfpro.realies.util.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

public object SpIcon

private var __AllIcons: List<ImageVector>? = null

public val SpIcon.AllIcons: List<ImageVector>
    get() {
        if (__AllIcons != null) {
            return __AllIcons!!
        }
        __AllIcons = listOf(
            IcCheck,
            IcClose,
            IcMenuAi,
            IcMenuImage,
            IcMenuImageAdd,
            IcMenuTextmin,
            IcRealieslogo,
            IcReport
        )
        return __AllIcons!!
    }

@Preview
@Composable
fun PreviewAllIcons() {
    Column {
        SpIcon.AllIcons.forEach {
            Image(imageVector = it, contentDescription = null)
        }
    }
}
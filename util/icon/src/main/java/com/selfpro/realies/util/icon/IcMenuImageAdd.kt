package com.selfpro.realies.util.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.selfpro.realies.util.common.SpColor

public val SpIcon.IcMenuImageAdd: ImageVector
    get() {
        if (`_icMenuImageAdd-2` != null) {
            return `_icMenuImageAdd-2`!!
        }
        `_icMenuImageAdd-2` = Builder(name = "IcMenuImageAdd-2", defaultWidth = 30.0.dp,
                defaultHeight = 30.0.dp, viewportWidth = 30.0f, viewportHeight = 30.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(SpColor.Black),
                    strokeLineWidth = 1.2f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(3.0f, 15.5f)
                horizontalLineTo(14.5f)
                moveTo(26.0f, 15.5f)
                horizontalLineTo(14.5f)
                moveTo(14.5f, 27.0f)
                verticalLineTo(15.5f)
                moveTo(14.5f, 15.5f)
                verticalLineTo(4.0f)
            }
        }
        .build()
        return `_icMenuImageAdd-2`!!
    }

private var `_icMenuImageAdd-2`: ImageVector? = null

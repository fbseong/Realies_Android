package com.selfpro.realies.util.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val SpIcon.IcReport: ImageVector
    get() {
        if (_icReport != null) {
            return _icReport!!
        }
        _icReport = Builder(name = "IcReport", defaultWidth = 30.0.dp, defaultHeight = 30.0.dp,
                viewportWidth = 30.0f, viewportHeight = 30.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.2f, strokeLineCap = Butt, strokeLineJoin = Round,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(22.5f, 21.875f)
                horizontalLineTo(7.5f)
                verticalLineTo(13.125f)
                curveTo(7.5f, 8.983f, 10.858f, 5.625f, 15.0f, 5.625f)
                curveTo(19.142f, 5.625f, 22.5f, 8.983f, 22.5f, 13.125f)
                verticalLineTo(21.875f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.2f, strokeLineCap = StrokeCap.Companion.Round,
                    strokeLineJoin = Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(5.0f, 26.25f)
                horizontalLineTo(25.0f)
                moveTo(2.5f, 8.125f)
                lineTo(4.375f, 8.75f)
                moveTo(8.125f, 2.5f)
                lineTo(8.75f, 4.375f)
                moveTo(6.25f, 6.25f)
                lineTo(4.375f, 4.375f)
            }
        }
        .build()
        return _icReport!!
    }

private var _icReport: ImageVector? = null

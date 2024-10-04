package com.selfpro.realies.util.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.selfpro.realies.util.common.SpColor

public val SpIcon.IcCheck: ImageVector
    get() {
        if (_icCheck != null) {
            return _icCheck!!
        }
        _icCheck = Builder(name = "IcCheck", defaultWidth = 30.0.dp, defaultHeight = 30.0.dp,
                viewportWidth = 30.0f, viewportHeight = 30.0f).apply {
            path(fill = SolidColor(SpColor.Theme), stroke = SolidColor(SpColor.Theme),
                    strokeLineWidth = 1.2f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(6.0f, 13.5f)
                lineTo(12.0f, 19.5f)
                curveTo(12.5f, 20.0f, 12.5f, 20.0f, 13.0f, 19.5f)
                lineTo(25.0f, 7.5f)
            }
        }
        .build()
        return _icCheck!!
    }

private var _icCheck: ImageVector? = null

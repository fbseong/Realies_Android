package com.selfpro.realies.util.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.selfpro.realies.util.common.SpColor

public val SpIcon.IcClose: ImageVector
    get() {
        if (_icClose != null) {
            return _icClose!!
        }
        _icClose = Builder(name = "IcClose", defaultWidth = 30.0.dp, defaultHeight = 30.0.dp,
                viewportWidth = 30.0f, viewportHeight = 30.0f).apply {
            path(fill = SolidColor(SpColor.Theme), stroke = SolidColor(SpColor.Theme),
                    strokeLineWidth = 1.2f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(22.5f, 7.5f)
                lineTo(7.5f, 22.5f)
                moveTo(7.5f, 7.5f)
                lineTo(22.5f, 22.5f)
            }
        }
        .build()
        return _icClose!!
    }

private var _icClose: ImageVector? = null

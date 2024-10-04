package com.selfpro.realies.util.icon

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val SpIcon.IcMenuImage: ImageVector
    get() {
        if (_icMenuImage != null) {
            return _icMenuImage!!
        }
        _icMenuImage = Builder(name = "IcMenuImage", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(24.0f)
                verticalLineToRelative(24.0f)
                horizontalLineToRelative(-24.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(4.0f, 18.667f)
                horizontalLineTo(20.0f)
                lineTo(15.0f, 12.0f)
                lineTo(11.0f, 17.333f)
                lineTo(8.0f, 13.333f)
                lineTo(4.0f, 18.667f)
                close()
                moveTo(0.0f, 24.0f)
                verticalLineTo(0.0f)
                horizontalLineTo(24.0f)
                verticalLineTo(24.0f)
                horizontalLineTo(0.0f)
                close()
            }
        }
        .build()
        return _icMenuImage!!
    }

private var _icMenuImage: ImageVector? = null

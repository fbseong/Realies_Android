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

public val SpIcon.IcMenuTextmin: ImageVector
    get() {
        if (_icMenuTextmin != null) {
            return _icMenuTextmin!!
        }
        _icMenuTextmin = Builder(name = "IcMenuTextmin", defaultWidth = 24.0.dp, defaultHeight =
                24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(24.0f)
                verticalLineToRelative(24.0f)
                horizontalLineToRelative(-24.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(7.774f, 8.341f)
                verticalLineTo(6.745f)
                horizontalLineTo(16.226f)
                verticalLineTo(8.341f)
                horizontalLineTo(12.96f)
                verticalLineTo(17.35f)
                horizontalLineTo(11.055f)
                verticalLineTo(8.341f)
                horizontalLineTo(7.774f)
                close()
            }
            path(fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(14.64f, 17.0f)
                verticalLineTo(15.409f)
                horizontalLineTo(14.998f)
                verticalLineTo(15.679f)
                horizontalLineTo(15.018f)
                curveTo(15.084f, 15.497f, 15.244f, 15.389f, 15.455f, 15.389f)
                curveTo(15.67f, 15.389f, 15.825f, 15.498f, 15.885f, 15.679f)
                horizontalLineTo(15.903f)
                curveTo(15.972f, 15.503f, 16.149f, 15.389f, 16.38f, 15.389f)
                curveTo(16.672f, 15.389f, 16.877f, 15.576f, 16.875f, 15.931f)
                verticalLineTo(17.0f)
                horizontalLineTo(16.503f)
                verticalLineTo(15.989f)
                curveTo(16.503f, 15.792f, 16.383f, 15.702f, 16.231f, 15.702f)
                curveTo(16.048f, 15.702f, 15.941f, 15.827f, 15.941f, 16.007f)
                verticalLineTo(17.0f)
                horizontalLineTo(15.578f)
                verticalLineTo(15.975f)
                curveTo(15.576f, 15.809f, 15.468f, 15.702f, 15.308f, 15.702f)
                curveTo(15.147f, 15.702f, 15.015f, 15.835f, 15.015f, 16.033f)
                verticalLineTo(17.0f)
                horizontalLineTo(14.64f)
                close()
                moveTo(17.233f, 17.0f)
                verticalLineTo(15.409f)
                horizontalLineTo(17.608f)
                verticalLineTo(17.0f)
                horizontalLineTo(17.233f)
                close()
                moveTo(17.206f, 14.979f)
                curveTo(17.206f, 14.867f, 17.305f, 14.776f, 17.423f, 14.776f)
                curveTo(17.542f, 14.776f, 17.64f, 14.867f, 17.64f, 14.979f)
                curveTo(17.64f, 15.091f, 17.542f, 15.182f, 17.423f, 15.184f)
                curveTo(17.305f, 15.182f, 17.206f, 15.091f, 17.206f, 14.979f)
                close()
                moveTo(18.34f, 16.068f)
                verticalLineTo(17.0f)
                horizontalLineTo(17.965f)
                verticalLineTo(15.409f)
                horizontalLineTo(18.323f)
                verticalLineTo(15.679f)
                horizontalLineTo(18.343f)
                curveTo(18.415f, 15.502f, 18.578f, 15.389f, 18.815f, 15.389f)
                curveTo(19.145f, 15.389f, 19.361f, 15.606f, 19.36f, 15.986f)
                verticalLineTo(17.0f)
                horizontalLineTo(18.988f)
                verticalLineTo(16.045f)
                curveTo(18.988f, 15.831f, 18.872f, 15.704f, 18.677f, 15.705f)
                curveTo(18.479f, 15.704f, 18.34f, 15.837f, 18.34f, 16.068f)
                close()
            }
        }
        .build()
        return _icMenuTextmin!!
    }

private var _icMenuTextmin: ImageVector? = null

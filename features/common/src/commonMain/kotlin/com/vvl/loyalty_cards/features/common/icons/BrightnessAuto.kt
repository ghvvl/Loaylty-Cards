package com.vvl.loyalty_cards.features.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val BrightnessAuto: ImageVector
    get() {
        if (_BrightnessAuto != null) {
            return _BrightnessAuto!!
        }
        _BrightnessAuto = ImageVector.Builder(
            name = "BrightnessAuto",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(312f, 640f)
                horizontalLineToRelative(64f)
                lineToRelative(32f, -92f)
                horizontalLineToRelative(146f)
                lineToRelative(32f, 92f)
                horizontalLineToRelative(62f)
                lineTo(512f, 280f)
                horizontalLineToRelative(-64f)
                lineTo(312f, 640f)
                close()
                moveTo(426f, 496f)
                lineTo(478f, 346f)
                horizontalLineToRelative(4f)
                lineToRelative(52f, 150f)
                lineTo(426f, 496f)
                close()
                moveTo(480f, 932f)
                lineTo(346f, 800f)
                lineTo(160f, 800f)
                verticalLineToRelative(-186f)
                lineTo(28f, 480f)
                lineToRelative(132f, -134f)
                verticalLineToRelative(-186f)
                horizontalLineToRelative(186f)
                lineToRelative(134f, -132f)
                lineToRelative(134f, 132f)
                horizontalLineToRelative(186f)
                verticalLineToRelative(186f)
                lineToRelative(132f, 134f)
                lineToRelative(-132f, 134f)
                verticalLineToRelative(186f)
                lineTo(614f, 800f)
                lineTo(480f, 932f)
                close()
                moveTo(480f, 820f)
                lineTo(580f, 720f)
                horizontalLineToRelative(140f)
                verticalLineToRelative(-140f)
                lineToRelative(100f, -100f)
                lineToRelative(-100f, -100f)
                verticalLineToRelative(-140f)
                lineTo(580f, 240f)
                lineTo(480f, 140f)
                lineTo(380f, 240f)
                lineTo(240f, 240f)
                verticalLineToRelative(140f)
                lineTo(140f, 480f)
                lineToRelative(100f, 100f)
                verticalLineToRelative(140f)
                horizontalLineToRelative(140f)
                lineToRelative(100f, 100f)
                close()
                moveTo(480f, 480f)
                close()
            }
        }.build()

        return _BrightnessAuto!!
    }

@Suppress("ObjectPropertyName")
private var _BrightnessAuto: ImageVector? = null

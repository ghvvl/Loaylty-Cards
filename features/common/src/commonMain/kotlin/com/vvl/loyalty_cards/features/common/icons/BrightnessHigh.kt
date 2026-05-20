package com.vvl.loyalty_cards.features.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val BrightnessHigh: ImageVector
    get() {
        if (_BrightnessHigh != null) {
            return _BrightnessHigh!!
        }
        _BrightnessHigh = ImageVector.Builder(
            name = "BrightnessHigh",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
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
                moveTo(621.5f, 621.5f)
                quadTo(680f, 563f, 680f, 480f)
                reflectiveQuadToRelative(-58.5f, -141.5f)
                quadTo(563f, 280f, 480f, 280f)
                reflectiveQuadToRelative(-141.5f, 58.5f)
                quadTo(280f, 397f, 280f, 480f)
                reflectiveQuadToRelative(58.5f, 141.5f)
                quadTo(397f, 680f, 480f, 680f)
                reflectiveQuadToRelative(141.5f, -58.5f)
                close()
                moveTo(480f, 480f)
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

        return _BrightnessHigh!!
    }

@Suppress("ObjectPropertyName")
private var _BrightnessHigh: ImageVector? = null
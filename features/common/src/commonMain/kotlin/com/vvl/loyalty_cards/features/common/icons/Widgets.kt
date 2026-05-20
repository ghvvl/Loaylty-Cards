package com.vvl.loyalty_cards.features.common.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Widgets: ImageVector
    get() {
        if (_Widgets != null) {
            return _Widgets!!
        }
        _Widgets = ImageVector.Builder(
            name = "Widgets",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(666f, 520f)
                lineTo(440f, 294f)
                lineToRelative(226f, -226f)
                lineToRelative(226f, 226f)
                lineToRelative(-226f, 226f)
                close()
                moveTo(120f, 440f)
                verticalLineToRelative(-320f)
                horizontalLineToRelative(320f)
                verticalLineToRelative(320f)
                lineTo(120f, 440f)
                close()
                moveTo(520f, 840f)
                verticalLineToRelative(-320f)
                horizontalLineToRelative(320f)
                verticalLineToRelative(320f)
                lineTo(520f, 840f)
                close()
                moveTo(120f, 840f)
                verticalLineToRelative(-320f)
                horizontalLineToRelative(320f)
                verticalLineToRelative(320f)
                lineTo(120f, 840f)
                close()
                moveTo(200f, 360f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(-160f)
                lineTo(200f, 200f)
                verticalLineToRelative(160f)
                close()
                moveTo(667f, 408f)
                lineTo(780f, 295f)
                lineTo(667f, 182f)
                lineTo(554f, 295f)
                lineTo(667f, 408f)
                close()
                moveTo(600f, 760f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(-160f)
                lineTo(600f, 600f)
                verticalLineToRelative(160f)
                close()
                moveTo(200f, 760f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(-160f)
                lineTo(200f, 600f)
                verticalLineToRelative(160f)
                close()
                moveTo(360f, 360f)
                close()
                moveTo(554f, 295f)
                close()
                moveTo(360f, 600f)
                close()
                moveTo(600f, 600f)
                close()
            }
        }.build()

        return _Widgets!!
    }

@Suppress("ObjectPropertyName")
private var _Widgets: ImageVector? = null
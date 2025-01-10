package com.vvl.loyalty_cards.impl.widget

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.size

internal class LoyaltyCardsWidget : GlanceAppWidget() {

    override val sizeMode: SizeMode = SizeMode.Exact

    override fun onCompositionError(
        context: Context,
        glanceId: GlanceId,
        appWidgetId: Int,
        throwable: Throwable
    ) {
        throwable.printStackTrace()
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                val size = LocalSize.current

                val iw: Dp
                val ih: Dp

                if (size.height * 2.5f < size.width) {
                    ih = size.height
                    iw = ih * 2.5f
                } else {
                    iw = size.width
                    ih = iw / 2.5f
                }

                Box(
                    modifier = GlanceModifier.fillMaxSize()
                        .background(GlanceTheme.colors.widgetBackground),
                    contentAlignment = Alignment.Center
                ) {
                    val backgroundColor =
                        GlanceTheme.colors.widgetBackground.getColor(LocalContext.current).toArgb()
                    val accentColor =
                        GlanceTheme.colors.primary.getColor(LocalContext.current).toArgb()


                    val bitmap = "150806000780787".encodeToBitmap(
                        500,
                        250,
                        backgroundColor,
                        accentColor
                    )
                    Image(
                        modifier = GlanceModifier.size(width = iw, height = ih),
                        provider = ImageProvider(bitmap),
                        contentDescription = "barcode",
                    )
                }
            }
        }
    }

    private data class BitmapImageProvider2(val bitmap: Bitmap) : ImageProvider {
        override fun toString() =
            "BitmapImageProvider(bitmap=Bitmap(${bitmap.width}px x ${bitmap.height}px))"
    }
}
package com.vvl.loyalty_cards

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.size
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

internal class MainWidget : GlanceAppWidget() {

    private val barcodeEncoder = BarcodeEncoder()

    override val sizeMode: SizeMode = SizeMode.Exact

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
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
                modifier = GlanceModifier.fillMaxSize().background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                val bitmap =
                    barcodeEncoder.encodeBitmap(
                        "150806000780787",
                        BarcodeFormat.CODE_128,
                        500,
                        250
                    )
                Image(
                    modifier = GlanceModifier.size(width = iw, height = ih),
                    provider = ImageProvider(bitmap),
                    contentDescription = "barcode"
                )
            }
        }
    }
}
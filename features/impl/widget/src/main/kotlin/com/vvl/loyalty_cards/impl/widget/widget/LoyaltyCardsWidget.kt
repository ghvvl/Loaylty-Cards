package com.vvl.loyalty_cards.impl.widget.widget

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.unit.Dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
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
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.impl.widget.utils.toBarCodeType
import io.github.alexzhirkevich.qrose.oned.BarcodePainter
import io.github.alexzhirkevich.qrose.toImageBitmap
import org.koin.compose.koinInject

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

    @Suppress("MagicNumber")
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
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(GlanceTheme.colors.widgetBackground),
                    contentAlignment = Alignment.Center
                ) {
                    val storage: LoyaltyCardsStorage = koinInject()
                    val cards by storage.loyaltyCards.collectAsState(emptyList())

                    val card = cards.firstOrNull() ?: return@Box
                    Image(
                        modifier = GlanceModifier.size(width = iw, height = ih),
                        provider = ImageProvider(
                            BarcodePainter(card.data, card.codeType.toBarCodeType())
                                .toImageBitmap(500, 250)
                                .asAndroidBitmap()
                        ),
                        contentDescription = "back",
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.primary)
                    )
                }
            }
        }
    }
}
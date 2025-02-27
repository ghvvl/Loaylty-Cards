package com.vvl.loyalty_cards.features.impl.widget.widget

import android.content.ComponentName
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.Visibility
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.items
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.visibility
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksProvider
import com.vvl.loyalty_cards.features.common.utils.toBarCodeType
import com.vvl.loyalty_cards.features.impl.widget.R
import io.github.alexzhirkevich.qrose.QrCodePainter
import io.github.alexzhirkevich.qrose.oned.BarcodePainter
import io.github.alexzhirkevich.qrose.toImageBitmap
import org.koin.mp.KoinPlatform
import kotlin.math.ceil

const val WIDGET_KEY_NAME = "widget"

internal class Widget : GlanceAppWidget() {
    private val keyWidget = ActionParameters.Key<String>(WIDGET_KEY_NAME)

    private val storage: LoyaltyCardsStorage by lazy { KoinPlatform.getKoin().get() }
    private val activityComponent: ComponentName by lazy { KoinPlatform.getKoin().get() }
    private val deepLinksProvider: DeepLinksProvider by lazy { KoinPlatform.getKoin().get() }

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
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(GlanceTheme.colors.primary),
                    contentAlignment = Alignment.Center
                ) {
                    val cards by storage.loyaltyCards.collectAsState(emptyList())
                    LazyColumn(GlanceModifier.fillMaxSize()) {
                        items(cards) {
                            CardItem(it, size)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun CardItem(loyaltyCard: LoyaltyCard, size: DpSize) {
        Column(
            modifier = GlanceModifier
                .size(width = size.width, height = size.height)
                .clickable(
                    actionStartActivity(
                        activityComponent,
                        actionParametersOf(
                            keyWidget to deepLinksProvider.createOpenCardDetailsDeepLink(loyaltyCard.data)
                        )
                    )
                )
                .padding(32.dp),
            horizontalAlignment = Alignment.Horizontal.CenterHorizontally
        ) {
            Text(
                modifier = GlanceModifier
                    .visibility(if (loyaltyCard.name.isNotBlank()) Visibility.Visible else Visibility.Gone)
                    .background(ImageProvider(R.drawable.text_background))
                    .padding(8.dp),
                text = loyaltyCard.name,
                style = TextStyle(textAlign = TextAlign.Center)
            )
            val dataBitmap = remember(loyaltyCard.data) {
                if (loyaltyCard.codeType == LoyaltyCardCodeType.QR_CODE) {
                    QrCodePainter(loyaltyCard.data).let {
                        it.toImageBitmap(
                            it.intrinsicSize.width.toInt(),
                            it.intrinsicSize.height.toInt()
                        )
                    }
                } else {
                    BarcodePainter(loyaltyCard.data, loyaltyCard.codeType.toBarCodeType())
                        .let {
                            it.toImageBitmap(
                                ceil(it.intrinsicSize.width).toInt(),
                                ceil(it.intrinsicSize.height).toInt()
                            )
                        }
                }
            }
            Image(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                provider = ImageProvider(dataBitmap.asAndroidBitmap()),
                contentDescription = "Localized description",
                colorFilter = ColorFilter.tint(GlanceTheme.colors.widgetBackground)
            )
        }
    }
}

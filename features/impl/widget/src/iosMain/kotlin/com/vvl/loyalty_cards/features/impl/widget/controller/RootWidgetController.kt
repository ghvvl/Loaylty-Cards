package com.vvl.loyalty_cards.features.impl.widget.controller

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.common.model.WidgetState
import com.vvl.loyalty_cards.data.storage.api.widget.storage.WidgetStorage
import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksProvider
import com.vvl.loyalty_cards.features.common.utils.toBarCodeType
import com.vvl.loyalty_cards.features.impl.widget.delegate.IOSWidgetDelegateImpl
import io.github.alexzhirkevich.qrose.QrCodePainter
import io.github.alexzhirkevich.qrose.oned.BarcodePainter
import io.github.alexzhirkevich.qrose.toByteArray
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.memScoped
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.Foundation.NSData
import platform.Foundation.create
import kotlin.math.ceil

class RootWidgetController : KoinComponent {

    private val widgetStorage: WidgetStorage by inject()
    private val widgetDelegate: IOSWidgetDelegateImpl by inject()
    private val deepLinksProvider: DeepLinksProvider by inject()

    suspend fun createWidgetState(widgetId: WidgetId) {
        widgetStorage.createWidgetStateIfNeeded(widgetId)
    }

    suspend fun getCurrentWidgetState(
        widgetId: WidgetId
    ): WidgetState = widgetStorage.getWidgetStateFlow(widgetId).first()

    fun setUpdateAllWidgetsCallback(callback: () -> Unit) {
        widgetDelegate.updateAllWidgetsCallback = callback
    }

    fun setGetAllWidgetsCallback(callback: ((List<String>) -> Unit) -> Unit) {
        widgetDelegate.getAllWidgetsCallback = callback
    }

    fun mapToNSData(card: LoyaltyCard): NSData {
        return if (card.codeType == LoyaltyCardCodeType.QR_CODE) {
            QrCodePainter(card.data).let {
                it.toByteArray(
                    it.intrinsicSize.width.toInt(),
                    it.intrinsicSize.height.toInt()
                ).toData()
            }
        } else {
            BarcodePainter(card.data, card.codeType.toBarCodeType()).let {
                it.toByteArray(
                    ceil(it.intrinsicSize.width).toInt(),
                    ceil(it.intrinsicSize.height).toInt()
                ).toData()
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    fun ByteArray.toData(): NSData = memScoped {
        NSData.create(
            bytes = allocArrayOf(this@toData),
            length = this@toData.size.toULong()
        )
    }

    fun createDeeplinkForCard(card: LoyaltyCard): String =
        deepLinksProvider.createOpenCardDetailsDeepLink(card.data)

    fun createDeeplinkForWidget(widgetId: WidgetId): String =
        deepLinksProvider.createOpenWidgetStateDetailsDeeplink(widgetId)
}

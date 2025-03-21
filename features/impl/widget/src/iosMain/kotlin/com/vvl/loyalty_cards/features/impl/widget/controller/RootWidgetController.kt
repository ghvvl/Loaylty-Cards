package com.vvl.loyalty_cards.features.impl.widget.controller

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
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

    private val storage: LoyaltyCardsStorage by inject()
    private val widgetDelegate: IOSWidgetDelegateImpl by inject()

    suspend fun current(): List<LoyaltyCard> = storage.loyaltyCards.first()

    fun setCallback(callback: () -> Unit) {
        widgetDelegate.callback = callback
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
}

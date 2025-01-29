package com.vvl.loyalty_cards.impl.widget

import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import io.github.alexzhirkevich.qrose.oned.BarcodeType

internal fun LoyaltyCardCodeType.toBarCodeType(): BarcodeType = when (this) {
    LoyaltyCardCodeType.QR -> throw IllegalArgumentException("QR is not barcode!")
    LoyaltyCardCodeType.Codabar -> BarcodeType.Codabar
    LoyaltyCardCodeType.Code39 -> BarcodeType.Code39
    LoyaltyCardCodeType.Code93 -> BarcodeType.Code93
    LoyaltyCardCodeType.Code128 -> BarcodeType.Code128
    LoyaltyCardCodeType.EAN8 -> BarcodeType.EAN8
    LoyaltyCardCodeType.EAN13 -> BarcodeType.EAN13
    LoyaltyCardCodeType.ITF -> BarcodeType.ITF
    LoyaltyCardCodeType.UPCA -> BarcodeType.UPCA
    LoyaltyCardCodeType.UPCE -> BarcodeType.UPCE
}

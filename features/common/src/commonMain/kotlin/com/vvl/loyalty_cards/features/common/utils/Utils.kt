package com.vvl.loyalty_cards.features.common.utils

import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import io.github.alexzhirkevich.qrose.oned.BarcodeType

fun LoyaltyCardCodeType.toBarCodeType(): BarcodeType = when (this) {
    LoyaltyCardCodeType.QR_CODE -> throw IllegalArgumentException("QR is not barcode!")
    LoyaltyCardCodeType.CODABAR -> BarcodeType.Codabar
    LoyaltyCardCodeType.CODE_39 -> BarcodeType.Code39
    LoyaltyCardCodeType.CODE_93 -> BarcodeType.Code93
    LoyaltyCardCodeType.CODE_128 -> BarcodeType.Code128
    LoyaltyCardCodeType.EAN_8 -> BarcodeType.EAN8
    LoyaltyCardCodeType.EAN_13 -> BarcodeType.EAN13
    LoyaltyCardCodeType.ITF -> BarcodeType.ITF
    LoyaltyCardCodeType.UPC_A -> BarcodeType.UPCA
    LoyaltyCardCodeType.UPC_E -> BarcodeType.UPCE
    else -> throw IllegalArgumentException("$this is not supported!")
}

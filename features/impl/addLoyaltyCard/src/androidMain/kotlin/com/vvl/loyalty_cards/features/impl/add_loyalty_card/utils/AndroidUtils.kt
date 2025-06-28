package com.vvl.loyalty_cards.features.impl.add_loyalty_card.utils

import com.google.mlkit.vision.barcode.common.Barcode
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType

internal fun Int.mapToLoyaltyCardCodeType(): LoyaltyCardCodeType = when (this) {
    Barcode.FORMAT_CODE_128 -> LoyaltyCardCodeType.CODE_128
    Barcode.FORMAT_CODE_39 -> LoyaltyCardCodeType.CODE_39
    Barcode.FORMAT_CODE_93 -> LoyaltyCardCodeType.CODE_93
    Barcode.FORMAT_CODABAR -> LoyaltyCardCodeType.CODABAR
    Barcode.FORMAT_DATA_MATRIX -> LoyaltyCardCodeType.DATA_MATRIX
    Barcode.FORMAT_EAN_13 -> LoyaltyCardCodeType.EAN_13
    Barcode.FORMAT_EAN_8 -> LoyaltyCardCodeType.EAN_8
    Barcode.FORMAT_ITF -> LoyaltyCardCodeType.ITF
    Barcode.FORMAT_QR_CODE -> LoyaltyCardCodeType.QR_CODE
    Barcode.FORMAT_UPC_A -> LoyaltyCardCodeType.UPC_A
    Barcode.FORMAT_UPC_E -> LoyaltyCardCodeType.UPC_E
    Barcode.FORMAT_PDF417 -> LoyaltyCardCodeType.PDF_417
    Barcode.FORMAT_AZTEC -> LoyaltyCardCodeType.AZTEC
    else -> throw IllegalArgumentException("barcode with type $this is not supported!")
}

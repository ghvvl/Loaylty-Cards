package com.vvl.loyalty_cards.common.model

import kotlinx.serialization.Serializable

@Serializable
enum class LoyaltyCardCodeType {
    QR,
    Codabar,
    Code39,
    Code93,
    Code128,
    EAN8,
    EAN13,
    ITF,
    UPCA,
    UPCE
}
//Barcode.FORMAT_DATA_MATRIX
//Barcode.FORMAT_PDF417
//Barcode.FORMAT_AZTEC
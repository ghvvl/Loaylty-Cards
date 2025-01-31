package com.vvl.loyalty_cards.common.model

import kotlinx.serialization.Serializable

@Serializable
enum class LoyaltyCardCodeType {
    QR_CODE,
    CODABAR,
    CODE_39,
    CODE_93,
    CODE_128,
    EAN_8,
    EAN_13,
    ITF,
    UPC_A,
    UPC_E,
    DATA_MATRIX,
    PDF_417,
    AZTEC
}
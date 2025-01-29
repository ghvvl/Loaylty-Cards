package com.vvl.loyalty_cards.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoyaltyCard(
    @SerialName("date")
    val data: String,
    @SerialName("code_type")
    val codeType: LoyaltyCardCodeType
)
package com.vvl.loyalty_cards.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoyaltyCard(
    @SerialName("name")
    val name: String,
    @SerialName("data")
    val data: String,
    @SerialName("code_type")
    val codeType: LoyaltyCardCodeType,
    @SerialName("card_color")
    val cardColor: Int
)

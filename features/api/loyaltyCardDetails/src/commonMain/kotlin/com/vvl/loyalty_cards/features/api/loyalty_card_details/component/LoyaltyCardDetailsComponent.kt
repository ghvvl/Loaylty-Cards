package com.vvl.loyalty_cards.features.api.loyalty_card_details.component

import com.arkivanov.decompose.value.Value
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.features.api.loyalty_card_details.model.BrightnessMode

interface LoyaltyCardDetailsComponent {

    val loyaltyCard: Value<LoyaltyCard>

    val brightnessMode: Value<BrightnessMode>

    fun onResetClicked()

    fun onBrightnessClicked()

    fun onLoyaltyCardNameChanged(name: String)

    fun onBackClicked()
}

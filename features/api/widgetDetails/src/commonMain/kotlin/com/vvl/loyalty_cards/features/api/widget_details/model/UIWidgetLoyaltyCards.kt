package com.vvl.loyalty_cards.features.api.widget_details.model

import com.vvl.loyalty_cards.common.model.LoyaltyCard

data class UIWidgetLoyaltyCards(
    val card: LoyaltyCard,
    val isChecked: Boolean
)
package com.vvl.loyalty_cards.features.api.loyalty_card_details.component

import com.vvl.loyalty_cards.common.model.LoyaltyCard

interface LoyaltyCardDetailsComponent {

    val loyaltyCard: LoyaltyCard

    fun onBackClicked()
}
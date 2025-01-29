package com.vvl.loyalty_cards.api.root.navigator

import com.vvl.loyalty_cards.common.model.LoyaltyCard

interface RootNavigator {
    fun openLoyaltyCardsList()

    fun openLoyaltyCardDetails(card: LoyaltyCard)

    fun openAddLoyaltyCard()
}
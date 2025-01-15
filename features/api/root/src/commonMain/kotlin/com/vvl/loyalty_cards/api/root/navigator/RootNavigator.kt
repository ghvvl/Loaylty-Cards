package com.vvl.loyalty_cards.api.root.navigator

interface RootNavigator {
    fun openLoyaltyCardsList()

    fun openLoyaltyCardDetails(cardId: String)

    fun openAddLoyaltyCard()
}
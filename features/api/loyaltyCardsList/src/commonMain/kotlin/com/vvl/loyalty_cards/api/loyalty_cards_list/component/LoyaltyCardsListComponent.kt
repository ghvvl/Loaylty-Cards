package com.vvl.loyalty_cards.api.loyalty_cards_list.component

import kotlinx.coroutines.flow.Flow

interface LoyaltyCardsListComponent {

    val loyaltyCards: Flow<List<String>>

    fun onLoyaltyCardSwiped(cardId: String)

    fun onLoyaltyCardClicked(cardId: String)

    fun onAddLoyaltyCardClicked()
}
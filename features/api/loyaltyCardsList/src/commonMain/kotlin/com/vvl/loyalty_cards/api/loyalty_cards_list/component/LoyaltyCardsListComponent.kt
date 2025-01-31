package com.vvl.loyalty_cards.api.loyalty_cards_list.component

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import kotlinx.coroutines.flow.Flow

interface LoyaltyCardsListComponent {

    val loyaltyCards: Flow<List<LoyaltyCard>>

    fun onLoyaltyCardSwiped(card: LoyaltyCard)

    fun onLoyaltyCardClicked(card: LoyaltyCard)

    fun onAddLoyaltyCardClicked()
}
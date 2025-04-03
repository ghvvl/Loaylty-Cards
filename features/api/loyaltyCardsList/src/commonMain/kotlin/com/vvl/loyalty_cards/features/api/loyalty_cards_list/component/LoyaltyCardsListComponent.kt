package com.vvl.loyalty_cards.features.api.loyalty_cards_list.component

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import kotlinx.coroutines.flow.Flow

interface LoyaltyCardsListComponent {

    val loyaltyCards: Flow<List<LoyaltyCard>>

    fun onLoyaltyCardPositionChanged(from: Int, to: Int)

    fun onLoyaltyCardSwiped(card: LoyaltyCard)

    fun onLoyaltyCardClicked(card: LoyaltyCard)

    fun onAddLoyaltyCardClicked()
}

package com.vvl.loyalty_cards.impl.loyalty_cards_list.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.vvl.loyalty_cards.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.api.root.navigator.RootNavigator
import kotlinx.coroutines.launch

class LoyaltyCardsListComponentImpl(
    componentContext: ComponentContext,
    private val loyaltyCardsStorage: LoyaltyCardsStorage,
    private val rootNavigator: RootNavigator
) : LoyaltyCardsListComponent, ComponentContext by componentContext {

    override val loyaltyCards = loyaltyCardsStorage.loyaltyCards

    override fun onLoyaltyCardSwiped(cardId: String) {
        coroutineScope().launch {
            loyaltyCardsStorage.removeLoyaltyCard(cardId)
        }
    }

    override fun onLoyaltyCardClicked(cardId: String) {
        rootNavigator.openLoyaltyCardDetails(cardId)
    }

    override fun onAddLoyaltyCardClicked() {
        rootNavigator.openAddLoyaltyCard()
    }
}
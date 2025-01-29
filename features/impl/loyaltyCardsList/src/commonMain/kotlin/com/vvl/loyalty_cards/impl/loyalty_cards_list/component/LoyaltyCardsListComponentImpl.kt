package com.vvl.loyalty_cards.impl.loyalty_cards_list.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.vvl.loyalty_cards.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import kotlinx.coroutines.launch

class LoyaltyCardsListComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    private val loyaltyCardsStorage: LoyaltyCardsStorage,
) : LoyaltyCardsListComponent, ComponentContext by componentContext {

    override val loyaltyCards = loyaltyCardsStorage.loyaltyCards

    override fun onLoyaltyCardSwiped(card: LoyaltyCard) {
        coroutineScope().launch {
            loyaltyCardsStorage.removeLoyaltyCard(card)
        }
    }

    override fun onLoyaltyCardClicked(card: LoyaltyCard) {
        rootNavigator.openLoyaltyCardDetails(card)
    }

    override fun onAddLoyaltyCardClicked() {
        rootNavigator.openAddLoyaltyCard()
    }
}
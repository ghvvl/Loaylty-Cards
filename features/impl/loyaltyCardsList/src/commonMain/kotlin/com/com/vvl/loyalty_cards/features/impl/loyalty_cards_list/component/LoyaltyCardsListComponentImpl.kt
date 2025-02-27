package com.com.vvl.loyalty_cards.features.impl.loyalty_cards_list.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import kotlinx.coroutines.launch

internal class LoyaltyCardsListComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    private val loyaltyCardsStorage: LoyaltyCardsStorage,
    private val widgetDelegate: WidgetDelegate
) : LoyaltyCardsListComponent, ComponentContext by componentContext {

    override val loyaltyCards = loyaltyCardsStorage.loyaltyCards

    override fun onLoyaltyCardSwiped(card: LoyaltyCard) {
        coroutineScope().launch {
            loyaltyCardsStorage.removeLoyaltyCard(card)
            widgetDelegate.updateAllWidgets()
        }
    }

    override fun onLoyaltyCardClicked(card: LoyaltyCard) {
        rootNavigator.openLoyaltyCardDetails(card)
    }

    override fun onAddLoyaltyCardClicked() {
        rootNavigator.openAddLoyaltyCard()
    }
}

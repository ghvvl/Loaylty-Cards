package com.vvl.loyalty_cards.impl.loyalty_cards_list.component

import com.arkivanov.decompose.ComponentContext
import com.vvl.loyalty_cards.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.api.root.navigator.RootNavigator

class LoyaltyCardsListComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator
) : LoyaltyCardsListComponent, ComponentContext by componentContext
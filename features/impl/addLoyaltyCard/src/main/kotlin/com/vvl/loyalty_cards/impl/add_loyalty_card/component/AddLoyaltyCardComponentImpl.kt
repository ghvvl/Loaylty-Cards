package com.vvl.loyalty_cards.impl.add_loyalty_card.component

import com.arkivanov.decompose.ComponentContext
import com.vvl.loyalty_cards.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.api.root.navigator.RootNavigator

class AddLoyaltyCardComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator
) : AddLoyaltyCardComponent, ComponentContext by componentContext
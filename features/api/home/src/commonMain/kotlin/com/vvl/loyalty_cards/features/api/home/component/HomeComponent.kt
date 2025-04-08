package com.vvl.loyalty_cards.features.api.home.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent

interface HomeComponent {

    val childStack: Value<ChildStack<*, HomeChild>>

    fun onLoyaltyCardsListClicked()

    fun onWidgetStatesListClicked()

    sealed interface HomeChild {
        class LoyaltyCardsList(val component: LoyaltyCardsListComponent) : HomeChild
    }
}
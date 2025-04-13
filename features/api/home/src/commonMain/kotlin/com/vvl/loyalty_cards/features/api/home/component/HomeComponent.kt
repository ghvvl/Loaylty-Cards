package com.vvl.loyalty_cards.features.api.home.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.api.widgets_list.component.WidgetsListComponent

interface HomeComponent {

    val childStack: Value<ChildStack<*, HomeChild>>

    fun onLoyaltyCardsListClicked()

    fun onWidgetStatesListClicked()

    sealed interface HomeChild {
        class LoyaltyCardsList(val component: LoyaltyCardsListComponent) : HomeChild
        class WidgetsList(val component: WidgetsListComponent) : HomeChild
    }
}

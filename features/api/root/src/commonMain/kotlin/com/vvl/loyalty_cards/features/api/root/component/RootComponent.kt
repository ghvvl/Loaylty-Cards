package com.vvl.loyalty_cards.features.api.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.api.home.component.HomeComponent
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.api.widget_details.component.WidgetDetailsComponent

interface RootComponent : BackHandlerOwner {
    val childStack: Value<ChildStack<*, RootChild>>

    fun onBackClicked()

    sealed interface RootChild {

        class Home(val component: HomeComponent) : RootChild

        class LoyaltyCardDetails(val component: LoyaltyCardDetailsComponent) : RootChild

        class AddLoyaltyCard(val component: AddLoyaltyCardComponent) : RootChild

        class WidgetDetails(val component: WidgetDetailsComponent) : RootChild
    }
}

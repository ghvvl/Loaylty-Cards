package com.vvl.loyalty_cards.api.root.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.vvl.loyalty_cards.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.api.loyalty_cards_list.component.LoyaltyCardsListComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, RootChild>>

    sealed interface RootChild {

        class LoyaltyCardsList(val component: LoyaltyCardsListComponent) : RootChild

        class LoyaltyCardDetails(val component: LoyaltyCardDetailsComponent) : RootChild

        class AddLoyaltyCard(val component: AddLoyaltyCardComponent) : RootChild
    }
}
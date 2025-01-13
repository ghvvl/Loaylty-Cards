package com.vvl.loyalty_cards.impl.root.view

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.vvl.loyalty_cards.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.api.root.component.RootComponent

@Composable
fun RootView(
    component: RootComponent,
    loyaltyCardsListView: @Composable (LoyaltyCardsListComponent) -> Unit,
    loyaltyCardDetailsView: @Composable (LoyaltyCardDetailsComponent) -> Unit,
    addLoyaltyCardView: @Composable (AddLoyaltyCardComponent) -> Unit
) = Children(component.childStack) {
    when (val child = it.instance) {
        is RootComponent.RootChild.LoyaltyCardsList -> {
            loyaltyCardsListView(child.component)
        }

        is RootComponent.RootChild.LoyaltyCardDetails -> {
            loyaltyCardDetailsView(child.component)
        }

        is RootComponent.RootChild.AddLoyaltyCard -> {
            addLoyaltyCardView(child.component)
        }
    }
}
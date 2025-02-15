package com.com.vvl.loyalty_cards.features.impl.root.view

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.com.vvl.loyalty_cards.features.impl.root.utils.backAnimation
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.api.root.component.RootComponent

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootView(
    component: RootComponent,
    loyaltyCardsListView: @Composable SharedTransitionScope.(LoyaltyCardsListComponent, Boolean) -> Unit,
    loyaltyCardDetailsView: @Composable SharedTransitionScope.(LoyaltyCardDetailsComponent, Boolean) -> Unit,
    addLoyaltyCardView: @Composable SharedTransitionScope.(AddLoyaltyCardComponent) -> Unit
) = SharedTransitionLayout(
    Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
) {
    Children(
        stack = component.childStack,
        modifier = Modifier.fillMaxSize(),
        animation = backAnimation(
            backHandler = component.backHandler,
            onBack = component::onBackClicked
        ),
    ) {
        when (val child = it.instance) {
            is RootComponent.RootChild.LoyaltyCardsList -> {
                loyaltyCardsListView(
                    child.component,
                    component.childStack.value.active.instance is RootComponent.RootChild.LoyaltyCardsList
                )
            }

            is RootComponent.RootChild.LoyaltyCardDetails -> {
                loyaltyCardDetailsView(
                    child.component,
                    component.childStack.value.active.instance is RootComponent.RootChild.LoyaltyCardDetails
                )
            }

            is RootComponent.RootChild.AddLoyaltyCard -> {
                addLoyaltyCardView(child.component)
            }
        }
    }
}

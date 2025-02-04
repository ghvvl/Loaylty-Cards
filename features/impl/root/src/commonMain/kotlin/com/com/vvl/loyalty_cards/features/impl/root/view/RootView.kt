package com.com.vvl.loyalty_cards.features.impl.root.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.predictiveBackAnimation
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.api.root.component.RootComponent

@Composable
fun RootView(
    component: RootComponent,
    loyaltyCardsListView: @Composable (LoyaltyCardsListComponent) -> Unit,
    loyaltyCardDetailsView: @Composable (LoyaltyCardDetailsComponent) -> Unit,
    addLoyaltyCardView: @Composable (AddLoyaltyCardComponent) -> Unit
) = Children(
    stack = component.childStack,
    modifier = Modifier.fillMaxSize(),
    animation = predictiveBackAnimation(
        backHandler = component.backHandler,
        fallbackAnimation = stackAnimation(fade() + scale()),
        onBack = component::onBackClicked,
    ),
) {
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
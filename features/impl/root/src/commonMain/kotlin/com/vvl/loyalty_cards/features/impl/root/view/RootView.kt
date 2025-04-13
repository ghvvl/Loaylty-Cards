package com.vvl.loyalty_cards.features.impl.root.view

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.experimental.stack.ChildStack
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.api.home.component.HomeComponent
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.vvl.loyalty_cards.features.api.widget_details.component.WidgetDetailsComponent
import com.vvl.loyalty_cards.features.impl.root.utils.backAnimation

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RootView(
    component: RootComponent,
    homeView: @Composable SharedTransitionScope.(
        HomeComponent,
        AnimatedVisibilityScope
    ) -> Unit,
    loyaltyCardDetailsView: @Composable SharedTransitionScope.(
        LoyaltyCardDetailsComponent,
        AnimatedVisibilityScope
    ) -> Unit,
    addLoyaltyCardView: @Composable SharedTransitionScope.(AddLoyaltyCardComponent) -> Unit,
    widgetDetailsView: @Composable SharedTransitionScope.(WidgetDetailsComponent, AnimatedVisibilityScope) -> Unit
) = SharedTransitionLayout(
    Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
) {
    ChildStack(
        stack = component.childStack,
        modifier = Modifier.fillMaxSize(),
        animation = backAnimation(
            backHandler = component.backHandler,
            onBack = component::onBackClicked
        )
    ) {
        when (val child = it.instance) {
            is RootComponent.RootChild.Home -> {
                homeView(child.component, this)
            }

            is RootComponent.RootChild.LoyaltyCardDetails -> {
                loyaltyCardDetailsView(
                    child.component,
                    this
                )
            }

            is RootComponent.RootChild.AddLoyaltyCard -> {
                addLoyaltyCardView(child.component)
            }

            is RootComponent.RootChild.WidgetDetails -> {
                widgetDetailsView(child.component, this)
            }
        }
    }
}

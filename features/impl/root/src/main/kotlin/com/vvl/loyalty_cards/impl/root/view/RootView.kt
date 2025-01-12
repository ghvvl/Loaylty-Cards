package com.vvl.loyalty_cards.impl.root.view

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.vvl.loyalty_cards.api.root.component.RootComponent

@Composable
fun RootView(component: RootComponent) = Children(component.childStack) {
    when (val child = it.instance) {
        is RootComponent.RootChild.LoyaltyCardsList -> {
            child.component
        }

        is RootComponent.RootChild.LoyaltyCardDetails -> {
            child.component
        }

        is RootComponent.RootChild.AddLoyaltyCard -> {
            child.component
        }
    }
}
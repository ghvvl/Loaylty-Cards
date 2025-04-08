package com.vvl.loyalty_cards.features.impl.home.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.vvl.loyalty_cards.features.api.home.component.HomeComponent
import com.vvl.loyalty_cards.features.api.home.model.LaunchMode
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import kotlinx.serialization.Serializable

internal class HomeComponentImpl(
    componentContext: ComponentContext,
    launchMode: LaunchMode,
    private val loyaltyCardsListComponent: (ComponentContext) -> LoyaltyCardsListComponent
) : HomeComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<HomeConfig>()

    override val childStack: Value<ChildStack<*, HomeComponent.HomeChild>> = childStack(
        source = navigation,
        serializer = HomeConfig.serializer(),
        initialConfiguration = when (launchMode) {
            is LaunchMode.LoyaltyCardsList -> HomeConfig.LoyaltyCardsList
        },
        childFactory = ::child,
        handleBackButton = true
    )

    private fun child(
        config: HomeConfig,
        componentContext: ComponentContext
    ): HomeComponent.HomeChild =
        when (config) {
            is HomeConfig.LoyaltyCardsList -> HomeComponent.HomeChild.LoyaltyCardsList(
                loyaltyCardsListComponent(componentContext)
            )
        }

    override fun onLoyaltyCardsListClicked() {
        navigation.bringToFront(HomeConfig.LoyaltyCardsList)
    }

    override fun onWidgetStatesListClicked() {

    }

    @Serializable
    private sealed interface HomeConfig {
        @Serializable
        data object LoyaltyCardsList : HomeConfig
    }
}
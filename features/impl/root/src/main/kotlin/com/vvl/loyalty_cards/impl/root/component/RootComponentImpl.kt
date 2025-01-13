package com.vvl.loyalty_cards.impl.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.vvl.loyalty_cards.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.api.root.component.RootComponent
import kotlinx.serialization.Serializable

class RootComponentImpl(
    componentContext: ComponentContext,
    val loyaltyCardsListComponent: (ComponentContext) -> LoyaltyCardsListComponent,
    val loyaltyCardDetailsComponent: (ComponentContext) -> LoyaltyCardDetailsComponent,
    val addLoyaltyCardComponent: (ComponentContext) -> AddLoyaltyCardComponent
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.RootChild>> = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = RootConfig.LoyaltyCardsList,
        childFactory = ::child,
        handleBackButton = true
    )

    private fun child(
        config: RootConfig,
        componentContext: ComponentContext
    ): RootComponent.RootChild =
        when (config) {
            RootConfig.LoyaltyCardsList -> RootComponent.RootChild.LoyaltyCardsList(
                loyaltyCardsListComponent(componentContext)
            )

            RootConfig.LoyaltyCardDetails -> RootComponent.RootChild.LoyaltyCardDetails(
                loyaltyCardDetailsComponent(componentContext)
            )

            RootConfig.AddLoyaltyCard -> RootComponent.RootChild.AddLoyaltyCard(
                addLoyaltyCardComponent(componentContext)
            )
        }

    @Serializable
    private sealed interface RootConfig {
        @Serializable
        data object LoyaltyCardsList : RootConfig

        @Serializable
        data object LoyaltyCardDetails : RootConfig

        @Serializable
        data object AddLoyaltyCard : RootConfig
    }
}
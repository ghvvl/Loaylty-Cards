package com.vvl.loyalty_cards.features.impl.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popToFirst
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.arkivanov.essenty.lifecycle.doOnCreate
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksHandler
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

internal class RootComponentImpl(
    componentContext: ComponentContext,
    val loyaltyCardsListComponent: (ComponentContext, RootNavigator) -> LoyaltyCardsListComponent,
    val loyaltyCardDetailsComponent: (
        ComponentContext,
        RootNavigator,
        LoyaltyCard
    ) -> LoyaltyCardDetailsComponent,
    val addLoyaltyCardComponent: (ComponentContext, RootNavigator) -> AddLoyaltyCardComponent,
    private val deepLinksHandler: DeepLinksHandler,
    private val loyaltyCardsStorage: LoyaltyCardsStorage
) : RootComponent, ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val navigation = StackNavigation<RootConfig>()

    private val rootNavigator = object : RootNavigator {
        override fun openLoyaltyCardsList() {
            navigation.popToFirst()
        }

        override fun openLoyaltyCardDetails(card: LoyaltyCard, replaceCurrent: Boolean) {
            val config = RootConfig.LoyaltyCardDetails(card)
            if (replaceCurrent) {
                navigation.replaceCurrent(config)
            } else {
                navigation.pushNew(config)
            }
        }

        override fun openAddLoyaltyCard() {
            navigation.pushNew(RootConfig.AddLoyaltyCard)
        }

        override fun onBackClicked() {
            navigation.pop()
        }
    }

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
            is RootConfig.LoyaltyCardsList -> RootComponent.RootChild.LoyaltyCardsList(
                loyaltyCardsListComponent(componentContext, rootNavigator)
            )

            is RootConfig.LoyaltyCardDetails -> RootComponent.RootChild.LoyaltyCardDetails(
                loyaltyCardDetailsComponent(componentContext, rootNavigator, config.loyaltyCard)
            )

            is RootConfig.AddLoyaltyCard -> RootComponent.RootChild.AddLoyaltyCard(
                addLoyaltyCardComponent(componentContext, rootNavigator)
            )
        }

    init {
        lifecycle.doOnCreate {
            deepLinksHandler
                .addOpenCardDetailsDeepLinkListener { cardId ->
                    scope.launch {
                        val loyaltyCard =
                            loyaltyCardsStorage.getLoyaltyCard(cardId) ?: return@launch
                        navigation.navigate {
                            listOf(
                                RootConfig.LoyaltyCardsList,
                                RootConfig.LoyaltyCardDetails(loyaltyCard)
                            )
                        }
                    }
                }
        }
        lifecycle.doOnDestroy { deepLinksHandler.removeOpenCardDetailsDeepLinkListener() }
    }

    override fun onBackClicked() {
        navigation.pop()
    }

    @Serializable
    private sealed interface RootConfig {
        @Serializable
        data object LoyaltyCardsList : RootConfig

        @Serializable
        data class LoyaltyCardDetails(val loyaltyCard: LoyaltyCard) : RootConfig

        @Serializable
        data object AddLoyaltyCard : RootConfig
    }
}

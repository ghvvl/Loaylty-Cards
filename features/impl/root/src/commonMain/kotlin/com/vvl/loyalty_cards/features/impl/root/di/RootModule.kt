package com.vvl.loyalty_cards.features.impl.root.di

import androidx.compose.animation.ExperimentalSharedTransitionApi
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.vvl.loyalty_cards.features.impl.root.component.RootComponentImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

@OptIn(ExperimentalSharedTransitionApi::class)
val rootModule = module {
    factory {
        RootComponentImpl(
            componentContext = it.get(),
            homeComponent = { context, navigator, launchMode ->
                get {
                    parametersOf(
                        context,
                        navigator,
                        launchMode
                    )
                }
            },
            loyaltyCardDetailsComponent = { context, navigator, loyaltyCard ->
                get { parametersOf(context, navigator, loyaltyCard) }
            },
            addLoyaltyCardComponent = { context, navigator ->
                get { parametersOf(context, navigator) }
            },
            widgetDetailsComponent = { context, navigator, widgetId ->
                get { parametersOf(context, navigator, widgetId) }
            },
            deepLinksHandler = get(),
            loyaltyCardsStorage = get(),
        )
    } bind RootComponent::class
}

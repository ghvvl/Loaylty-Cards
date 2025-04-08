package com.vvl.loyalty_cards.features.impl.home.di

import com.vvl.loyalty_cards.features.api.home.component.HomeComponent
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.impl.home.component.HomeComponentImpl
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    factory {
        HomeComponentImpl(
            componentContext = it.get(),
            launchMode = it.get(),
            loyaltyCardsListComponent = { context ->
                get {
                    parametersOf(
                        context,
                        it.get<RootNavigator>()
                    )
                }
            },
        )
    } bind HomeComponent::class
}

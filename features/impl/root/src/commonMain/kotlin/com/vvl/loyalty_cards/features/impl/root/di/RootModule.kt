package com.vvl.loyalty_cards.features.impl.root.di

import com.vvl.loyalty_cards.features.impl.root.component.RootComponentImpl
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

val rootModule = module {
    factory {
        RootComponentImpl(
            componentContext = it.get(),
            loyaltyCardsListComponent = { context, navigator -> get { parametersOf(context, navigator) } },
            loyaltyCardDetailsComponent = { context, navigator, loyaltyCard ->
                get { parametersOf(context, navigator, loyaltyCard) }
            },
            addLoyaltyCardComponent = { context, navigator -> get { parametersOf(context, navigator) } },
            deepLinksHandler = get(),
            loyaltyCardsStorage = get()
        )
    } bind RootComponent::class
}

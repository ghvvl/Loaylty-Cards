package com.com.vvl.loyalty_cards.features.impl.root.di

import com.com.vvl.loyalty_cards.features.impl.root.component.RootComponentImpl
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

val rootModule = module {
    factory {
        RootComponentImpl(
            it.get(),
            { context, navigator -> get { parametersOf(context, navigator) } },
            { context, navigator, loyaltyCard ->
                get { parametersOf(context, navigator, loyaltyCard) }
            },
            { context, navigator -> get { parametersOf(context, navigator) } },
        )
    } bind RootComponent::class
}
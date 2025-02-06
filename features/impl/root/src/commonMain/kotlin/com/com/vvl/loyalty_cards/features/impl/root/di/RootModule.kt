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
            { context, holder -> get { parametersOf(context, holder) } },
            { context, holder -> get { parametersOf(context, holder) } },
            { context, holder -> get { parametersOf(context, holder) } },
        )
    } bind RootComponent::class
}

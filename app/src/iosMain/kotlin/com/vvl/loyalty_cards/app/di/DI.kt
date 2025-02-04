package com.vvl.loyalty_cards.app.di

import com.vvl.loyalty_cards.impl.add_loyalty_card.di.addLoyaltyCardModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(listOf(appModule, addLoyaltyCardModule))
}
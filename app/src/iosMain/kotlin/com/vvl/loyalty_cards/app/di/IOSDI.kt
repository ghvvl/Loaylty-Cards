package com.vvl.loyalty_cards.app.di

import com.vvl.loyalty_cards.features.impl.add_loyalty_card.di.addLoyaltyCardModule
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.di.iosLoyaltyCardDetailsModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(listOf(appModule, addLoyaltyCardModule, iosLoyaltyCardDetailsModule))
}

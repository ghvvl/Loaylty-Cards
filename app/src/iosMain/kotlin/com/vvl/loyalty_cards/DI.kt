package com.vvl.loyalty_cards

import com.vvl.loyalty_cards.impl.add_loyalty_card.di.addLoyaltyCardModule
import com.vvl.loyalty_cards.impl.loyalty_cards.storage.loyaltyCardsModule
import org.koin.core.context.startKoin

fun initKoin() = startKoin {
    modules(listOf(appModule, loyaltyCardsModule, addLoyaltyCardModule))
}
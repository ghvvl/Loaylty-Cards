package com.vvl.loyalty_cards.app.di

import com.vvl.loyalty_cards.features.impl.add_loyalty_card.di.addLoyaltyCardModule
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.di.iosLoyaltyCardDetailsModule
import com.vvl.loyalty_cards.features.impl.widget.di.iosWidgetModule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

fun startKoin() = startKoin {
    modules(listOf(appModule, addLoyaltyCardModule, iosLoyaltyCardDetailsModule, iosWidgetModule))
}

fun stopKoin() = stopKoin()

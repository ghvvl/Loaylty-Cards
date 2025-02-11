package com.vvl.loyalty_cards.app.di

import com.com.vvl.loyalty_cards.features.impl.loyalty_cards_list.di.loyaltyCardsListModule
import com.com.vvl.loyalty_cards.features.impl.root.di.rootModule
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di.loyaltyCardsModule
import org.koin.dsl.module

internal val appModule = module {
    includes(
        loyaltyCardsModule,
        rootModule,
        loyaltyCardsListModule
    )
}

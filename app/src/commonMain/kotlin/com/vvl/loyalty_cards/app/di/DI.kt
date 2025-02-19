package com.vvl.loyalty_cards.app.di

import com.com.vvl.loyalty_cards.features.impl.loyalty_cards_list.di.loyaltyCardsListModule
import com.com.vvl.loyalty_cards.features.impl.root.di.rootModule
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di.loyaltyCardsModule
import com.vvl.loyalty_cards.features.impl.deep_links.di.deepLinksModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.dsl.module

internal val appModule = module {
    single<CoroutineScope> { MainScope() }
    includes(
        loyaltyCardsModule,
        rootModule,
        loyaltyCardsListModule,
        deepLinksModule
    )
}

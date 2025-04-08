package com.vvl.loyalty_cards.app.di

import com.vvl.loyalty_cards.data.storage.impl.database.di.databaseModule
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di.loyaltyCardsModule
import com.vvl.loyalty_cards.data.storage.impl.widget.di.widgetModule
import com.vvl.loyalty_cards.features.impl.deep_links.di.deepLinksModule
import com.vvl.loyalty_cards.features.impl.home.di.homeModule
import com.vvl.loyalty_cards.features.impl.loyalty_cards_list.di.loyaltyCardsListModule
import com.vvl.loyalty_cards.features.impl.root.di.rootModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.dsl.module

internal val appModule = module {
    single<CoroutineScope> { MainScope() }
    includes(
        databaseModule,
        loyaltyCardsModule,
        widgetModule,
        rootModule,
        loyaltyCardsListModule,
        homeModule,
        deepLinksModule
    )
}

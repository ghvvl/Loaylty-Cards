package com.vvl.loyalty_cards.data.storage.impl.database.di

import com.vvl.loyalty_cards.data.storage.impl.database.database.LoyaltyCardsDao
import com.vvl.loyalty_cards.data.storage.impl.database.database.LoyaltyCardsDatabase
import com.vvl.loyalty_cards.data.storage.impl.database.database.WidgetDao
import org.koin.dsl.module

val databaseModule = module {
    single<LoyaltyCardsDatabase> { createDatabase() }
    single<LoyaltyCardsDao> { get<LoyaltyCardsDatabase>().loyaltyCardsDao() }
    single<WidgetDao> { get<LoyaltyCardsDatabase>().widgetDao() }
}

internal expect fun createDatabase(): LoyaltyCardsDatabase

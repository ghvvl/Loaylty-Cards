package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di

import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database.LoyaltyCardsDao
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database.LoyaltyCardsDatabase
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.storage.LoyaltyCardsStorageImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loyaltyCardsModule = module {
    singleOf(::LoyaltyCardsStorageImpl) bind LoyaltyCardsStorage::class
    single<LoyaltyCardsDao> { createDatabase().loyaltyCardsDao() }
}

internal expect fun createDatabase(): LoyaltyCardsDatabase

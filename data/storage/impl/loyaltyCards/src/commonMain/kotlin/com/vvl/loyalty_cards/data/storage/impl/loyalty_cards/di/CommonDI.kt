package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di

import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.storage.LoyaltyCardsStorageImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loyaltyCardsModule = module {
    singleOf(::LoyaltyCardsStorageImpl) bind LoyaltyCardsStorage::class
    single<DataStore<List<LoyaltyCard>>> { getDataStore() }
}

expect fun getDataStore(): DataStore<List<LoyaltyCard>>

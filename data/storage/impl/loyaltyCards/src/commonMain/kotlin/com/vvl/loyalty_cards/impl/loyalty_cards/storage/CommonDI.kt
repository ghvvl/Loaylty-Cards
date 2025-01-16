package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import org.koin.dsl.module

val loyaltyCardsModule = module {
    single<DataStore<List<String>>> { getDataStore() }
}

expect fun getDataStore(): DataStore<List<String>>
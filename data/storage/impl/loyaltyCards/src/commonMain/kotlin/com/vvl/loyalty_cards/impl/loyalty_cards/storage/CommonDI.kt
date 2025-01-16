package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.koin.dsl.module

val loyaltyCardsModule = module {
    single<DataStore<Preferences>> { getDataStore() }
}

expect fun getDataStore(): DataStore<Preferences>
package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import org.koin.dsl.module

val loyaltyCardsModule = module {
    single<DataStore<List<LoyaltyCard>>> { getDataStore() }
}

expect fun getDataStore(): DataStore<List<LoyaltyCard>>
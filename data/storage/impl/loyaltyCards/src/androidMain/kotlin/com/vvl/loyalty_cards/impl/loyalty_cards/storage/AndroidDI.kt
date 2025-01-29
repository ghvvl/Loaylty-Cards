package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import android.content.Context
import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import org.koin.mp.KoinPlatform

actual fun getDataStore(): DataStore<List<LoyaltyCard>> = createDataStore(
    producePath = {
        KoinPlatform.getKoin().get<Context>().filesDir.resolve(dataStoreFileName).absolutePath
    }
)
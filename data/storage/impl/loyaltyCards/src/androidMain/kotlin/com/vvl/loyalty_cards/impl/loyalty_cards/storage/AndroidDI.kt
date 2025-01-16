package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import android.content.Context
import androidx.datastore.core.DataStore
import org.koin.mp.KoinPlatform

actual fun getDataStore(): DataStore<List<String>> = createDataStore(
    producePath = {
        KoinPlatform.getKoin().get<Context>().filesDir.resolve(dataStoreFileName).absolutePath
    }
)
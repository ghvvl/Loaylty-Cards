package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di

import android.content.Context
import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.utils.DATA_STORE_FILE_NAME
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.utils.createDataStore
import org.koin.mp.KoinPlatform

actual fun getDataStore(): DataStore<List<LoyaltyCard>> = createDataStore(
    producePath = {
        KoinPlatform.getKoin().get<Context>().filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
)
package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import android.content.Context
import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import okio.FileSystem
import okio.SYSTEM
import org.koin.mp.KoinPlatform

actual val fileSystem = FileSystem.SYSTEM

actual fun getDataStore(): DataStore<List<LoyaltyCard>> = createDataStore(
    producePath = {
        KoinPlatform.getKoin().get<Context>().filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
)
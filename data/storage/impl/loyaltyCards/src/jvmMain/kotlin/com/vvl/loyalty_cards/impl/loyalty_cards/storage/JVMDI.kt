package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import java.io.File

actual fun getDataStore(): DataStore<List<LoyaltyCard>> = createDataStore(
    producePath = { File(dataStoreFileName).absolutePath }
)
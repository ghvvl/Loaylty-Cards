package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di

import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.utils.DATA_STORE_FILE_NAME
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.utils.createDataStore
import java.io.File

actual fun getDataStore(): DataStore<List<LoyaltyCard>> = createDataStore(
    producePath = { File(DATA_STORE_FILE_NAME).absolutePath }
)

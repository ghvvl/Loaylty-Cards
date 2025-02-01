package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import okio.FileSystem
import java.io.File

actual val fileSystem = FileSystem.SYSTEM

actual fun getDataStore(): DataStore<List<LoyaltyCard>> = createDataStore(
    producePath = { File(DATA_STORE_FILE_NAME).absolutePath }
)
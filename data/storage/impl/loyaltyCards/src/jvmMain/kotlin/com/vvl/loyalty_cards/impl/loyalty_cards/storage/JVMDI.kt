package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import java.io.File

actual fun getDataStore(): DataStore<List<String>> = createDataStore(
    producePath = { File(dataStoreFileName).absolutePath }
)
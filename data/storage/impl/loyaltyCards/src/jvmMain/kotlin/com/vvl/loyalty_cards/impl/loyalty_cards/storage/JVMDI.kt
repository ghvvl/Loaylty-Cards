package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.io.File

actual fun getDataStore(): DataStore<Preferences> = createDataStore(
    producePath = { File(dataStoreFileName).absolutePath }
)
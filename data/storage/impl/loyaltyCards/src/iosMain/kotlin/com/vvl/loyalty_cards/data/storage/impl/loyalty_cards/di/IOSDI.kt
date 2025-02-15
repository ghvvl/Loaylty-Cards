package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di

import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.utils.DATA_STORE_FILE_NAME
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.utils.createDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual fun getDataStore(): DataStore<List<LoyaltyCard>> = createDataStore(
    producePath = {
        val documentDirectory: NSURL? =
            NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
        requireNotNull(documentDirectory).path + "/$DATA_STORE_FILE_NAME"
    }
)

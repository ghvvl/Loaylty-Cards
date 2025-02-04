package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.utils

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioSerializer
import androidx.datastore.core.okio.OkioStorage
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import kotlinx.serialization.json.okio.encodeToBufferedSink
import okio.BufferedSink
import okio.BufferedSource
import okio.FileSystem
import okio.Path.Companion.toPath

internal const val DATA_STORE_FILE_NAME = "datastore/loyalty_cards.preferences_pb"

expect val fileSystem: FileSystem

@Suppress("TooGenericExceptionCaught", "SwallowedException")
@OptIn(ExperimentalSerializationApi::class)
internal fun createDataStore(producePath: () -> String): DataStore<List<LoyaltyCard>> =
    DataStoreFactory.create(
        storage = OkioStorage(
            fileSystem,
            object : OkioSerializer<List<LoyaltyCard>> {
                override val defaultValue: List<LoyaltyCard> = emptyList()

                override suspend fun readFrom(source: BufferedSource): List<LoyaltyCard> =
                    try {
                        Json.decodeFromBufferedSource<List<LoyaltyCard>>(source)
                    } catch (e: Exception) {
                        emptyList()
                    }

                override suspend fun writeTo(t: List<LoyaltyCard>, sink: BufferedSink) {
                    Json.encodeToBufferedSink(t, sink)
                }
            }
        ) {
            producePath().toPath()
        }
    )
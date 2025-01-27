package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.okio.OkioSerializer
import androidx.datastore.core.okio.OkioStorage
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.okio.decodeFromBufferedSource
import kotlinx.serialization.json.okio.encodeToBufferedSink
import okio.BufferedSink
import okio.BufferedSource
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.SYSTEM

@OptIn(ExperimentalSerializationApi::class)
internal fun createDataStore(producePath: () -> String): DataStore<List<String>> =
    DataStoreFactory.create(
        storage = OkioStorage(
            FileSystem.SYSTEM,
            object : OkioSerializer<List<String>> {
                override val defaultValue: List<String> = emptyList()

                override suspend fun readFrom(source: BufferedSource): List<String> =
                    try {
                        Json.decodeFromBufferedSource<List<String>>(source)
                    } catch (e: Exception) {
                        emptyList()
                    }

                override suspend fun writeTo(t: List<String>, sink: BufferedSink) {
                    Json.encodeToBufferedSink(t, sink)
                }
            }
        ) {
            producePath().toPath()
        }
    )

internal const val dataStoreFileName = "datastore/loyalty_cards.preferences_pb"
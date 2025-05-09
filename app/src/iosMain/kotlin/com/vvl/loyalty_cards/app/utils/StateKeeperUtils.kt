package com.vvl.loyalty_cards.app.utils

import com.arkivanov.essenty.statekeeper.SerializableContainer
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.serialization.json.Json
import platform.Foundation.NSCoder
import platform.Foundation.NSString
import platform.Foundation.decodeTopLevelObjectOfClass
import platform.Foundation.encodeObject

private val json = Json { allowStructuredMapKeys = true }

@Suppress("unused") // Used in Swift
fun save(coder: NSCoder, state: SerializableContainer) {
    coder.encodeObject(
        `object` = json.encodeToString(SerializableContainer.serializer(), state),
        forKey = "state"
    )
}

@Suppress("unused", "TooGenericExceptionCaught", "SwallowedException") // Used in Swift
@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
fun restore(coder: NSCoder): SerializableContainer? {
    val decodedObject = coder.decodeTopLevelObjectOfClass(
        aClass = NSString,
        forKey = "state",
        error = null
    )
    return (decodedObject as String?)?.let {
        try {
            json.decodeFromString(SerializableContainer.serializer(), it)
        } catch (e: Exception) {
            null
        }
    }
}

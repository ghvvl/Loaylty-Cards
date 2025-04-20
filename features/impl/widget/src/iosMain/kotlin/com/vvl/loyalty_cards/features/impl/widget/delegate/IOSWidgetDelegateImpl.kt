package com.vvl.loyalty_cards.features.impl.widget.delegate

import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine

internal class IOSWidgetDelegateImpl : WidgetDelegate {

    var updateAllWidgetsCallback: () -> Unit = {}
    var getAllWidgetsCallback: ((List<String>) -> Unit) -> Unit = {}

    override suspend fun updateAllWidgets() {
        updateAllWidgetsCallback()
    }

    @OptIn(ExperimentalForeignApi::class)
    override suspend fun getAllWidgets(): List<WidgetId> =
        suspendCancellableCoroutine { continuation ->
            getAllWidgetsCallback {
                continuation.resumeWith(Result.success(it.map { WidgetId(it) }))
            }
        }
}

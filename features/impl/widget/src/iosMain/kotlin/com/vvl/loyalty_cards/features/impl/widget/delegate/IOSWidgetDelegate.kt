package com.vvl.loyalty_cards.features.impl.widget.delegate

import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate

internal class IOSWidgetDelegateImpl : WidgetDelegate {

    var callback: () -> Unit = {}

    override suspend fun updateAllWidgets() {
        callback()
    }
}
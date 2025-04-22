package com.vvl.loyalty_cards.features.api.widget.component

import com.vvl.loyalty_cards.common.model.WidgetId

interface WidgetDelegate {

    suspend fun updateAllWidgets()

    suspend fun getAllWidgets(): List<WidgetId>
}

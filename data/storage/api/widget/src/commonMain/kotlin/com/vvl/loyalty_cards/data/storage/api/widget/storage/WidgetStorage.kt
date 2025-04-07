package com.vvl.loyalty_cards.data.storage.api.widget.storage

import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.common.model.WidgetState
import kotlinx.coroutines.flow.Flow

interface WidgetStorage {
    val widgetStates: Flow<List<WidgetState>>

    suspend fun createWidgetStateIfNeeded(widgetId: WidgetId)

    suspend fun removeWidgetState(widgetId: WidgetId)

    fun getWidgetStateFlow(widgetId: WidgetId): Flow<WidgetState?>
}

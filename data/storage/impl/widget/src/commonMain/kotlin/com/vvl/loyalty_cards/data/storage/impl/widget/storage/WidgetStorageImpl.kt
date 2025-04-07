package com.vvl.loyalty_cards.data.storage.impl.widget.storage

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.common.model.WidgetState
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.data.storage.api.widget.storage.WidgetStorage
import com.vvl.loyalty_cards.data.storage.impl.database.database.WidgetDao
import com.vvl.loyalty_cards.data.storage.impl.database.model.DBWidgetState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

internal class WidgetStorageImpl(
    private val widgetDao: WidgetDao,
    private val loyaltyCardsStorage: LoyaltyCardsStorage
) : WidgetStorage {

    override val widgetStates: Flow<List<WidgetState>> =
        combine(
            widgetDao.getAllAsFlow(),
            loyaltyCardsStorage.loyaltyCards
        ) { widgetsStates, loyaltyCards ->
            widgetsStates.map { it.map(loyaltyCards) }
        }

    override suspend fun createWidgetStateIfNeeded(widgetId: WidgetId) {
        widgetDao.insert(DBWidgetState(widgetId, emptyList()))
    }

    override suspend fun removeWidgetState(widgetId: WidgetId) {
        widgetDao.deleteById(widgetId)
    }

    override fun getWidgetStateFlow(widgetId: WidgetId): Flow<WidgetState?> = widgetStates.map {
        it.firstOrNull { it.widgetId == widgetId && it.widgetCards.isNotEmpty() }
    }

    private fun DBWidgetState.map(loyaltyCards: List<LoyaltyCard>): WidgetState = WidgetState(
        widgetId = widgetId,
        widgetCards = loyaltyCards.filter { widgetCardsIds.contains(it.data) }
    )
}

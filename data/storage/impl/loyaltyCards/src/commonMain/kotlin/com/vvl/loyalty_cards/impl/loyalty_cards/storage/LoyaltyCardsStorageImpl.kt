package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.api.loyalty_cards.storage.LoyaltyCardsStorage
import kotlinx.coroutines.flow.Flow

class LoyaltyCardsStorageImpl(private val dataStore: DataStore<List<String>>) : LoyaltyCardsStorage {

    override val loyaltyCards: Flow<List<String>> = dataStore.data

    override suspend fun addLoyaltyCard(cardId: String) {
        dataStore.updateData { it + cardId }
    }

    override suspend fun removeLoyaltyCard(cardId: String) {
        dataStore.updateData { it - cardId }
    }
}
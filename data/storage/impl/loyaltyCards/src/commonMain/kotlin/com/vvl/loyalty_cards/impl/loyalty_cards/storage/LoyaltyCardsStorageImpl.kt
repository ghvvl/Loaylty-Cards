package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.vvl.loyalty_cards.api.loyalty_cards.storage.LoyaltyCardsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoyaltyCardsStorageImpl(private val dataStore: DataStore<Preferences>) : LoyaltyCardsStorage {

    private val key = stringSetPreferencesKey("cards")

    private fun Preferences.getCurrentValue(): Set<String> = get(key).orEmpty()

    override val loyaltyCards: Flow<Set<String>> =
        dataStore.data.map { it.getCurrentValue() }

    override suspend fun addLoyaltyCard(cardId: String) {
        dataStore.edit { it[key] = it.getCurrentValue() + cardId }
    }

    override suspend fun removeLoyaltyCard(cardId: String) {
        dataStore.edit { it[key] = it.getCurrentValue() - cardId }
    }
}
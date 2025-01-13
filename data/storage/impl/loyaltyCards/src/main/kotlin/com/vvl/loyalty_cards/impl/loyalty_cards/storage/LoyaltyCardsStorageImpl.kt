package com.vvl.loyalty_cards.impl.loyalty_cards.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.vvl.loyalty_cards.api.loyalty_cards.storage.LoyaltyCardsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoyaltyCardsStorageImpl(private val context: Context) : LoyaltyCardsStorage {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "loyalty_cards")

    private val key = stringSetPreferencesKey("cards")

    private fun Preferences.getCurrentValue(): Set<String> = get(key).orEmpty()

    override val loyaltyCards: Flow<Set<String>> =
        context.dataStore.data.map { it.getCurrentValue() }

    override suspend fun addLoyaltyCard(cardId: String) {
        context.dataStore.edit { it[key] = it.getCurrentValue() + cardId }
    }

    override suspend fun removeLoyaltyCard(cardId: String) {
        context.dataStore.edit { it.getCurrentValue() - cardId }
    }
}
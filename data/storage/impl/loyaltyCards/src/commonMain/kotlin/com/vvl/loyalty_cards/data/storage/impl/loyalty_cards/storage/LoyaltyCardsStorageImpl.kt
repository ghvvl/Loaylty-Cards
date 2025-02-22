package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.storage

import androidx.datastore.core.DataStore
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class LoyaltyCardsStorageImpl(
    private val dataStore: DataStore<List<LoyaltyCard>>
) : LoyaltyCardsStorage {

    override val loyaltyCards: Flow<List<LoyaltyCard>> = dataStore.data

    override suspend fun addLoyaltyCard(card: LoyaltyCard) = updateLoyaltyCard(card)

    override suspend fun getLoyaltyCard(cardData: String) =
        loyaltyCards.first().firstOrNull { it.data == cardData }

    override suspend fun updateLoyaltyCard(card: LoyaltyCard) {
        dataStore.updateData {
            val index = it.indexOfFirst { it.data == card.data }
            val newList = it.toMutableList()

            if (index == -1) {
                newList.add(0, card)
            } else {
                newList[index] = card
            }

            newList
        }
    }

    override suspend fun removeLoyaltyCard(card: LoyaltyCard) {
        dataStore.updateData { it - card }
    }
}

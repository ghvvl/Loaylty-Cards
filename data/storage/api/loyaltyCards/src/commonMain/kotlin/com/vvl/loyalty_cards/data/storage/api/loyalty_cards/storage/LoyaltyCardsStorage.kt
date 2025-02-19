package com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import kotlinx.coroutines.flow.Flow

interface LoyaltyCardsStorage {
    val loyaltyCards: Flow<List<LoyaltyCard>>

    suspend fun addLoyaltyCard(card: LoyaltyCard)

    suspend fun getLoyaltyCard(cardData: String): LoyaltyCard?

    suspend fun updateLoyaltyCard(card: LoyaltyCard)

    suspend fun removeLoyaltyCard(card: LoyaltyCard)
}

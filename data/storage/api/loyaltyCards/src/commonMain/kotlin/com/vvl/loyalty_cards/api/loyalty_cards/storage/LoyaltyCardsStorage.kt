package com.vvl.loyalty_cards.api.loyalty_cards.storage

import kotlinx.coroutines.flow.Flow

interface LoyaltyCardsStorage {
    val loyaltyCards: Flow<Set<String>>

    suspend fun addLoyaltyCard(cardId: String)

    suspend fun removeLoyaltyCard(cardId: String)
}
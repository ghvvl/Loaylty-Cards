package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.storage

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.database.LoyaltyCardsDao
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.model.DBLoyaltyCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal class LoyaltyCardsStorageImpl(
    private val loyaltyCardsDao: LoyaltyCardsDao
) : LoyaltyCardsStorage {

    override val loyaltyCards: Flow<List<LoyaltyCard>> =
        loyaltyCardsDao.getAllAsFlow().map { it.map { it.map() } }

    override suspend fun addLoyaltyCard(card: LoyaltyCard) = updateLoyaltyCard(card)

    override suspend fun getLoyaltyCard(cardData: String) =
        loyaltyCards.first().firstOrNull { it.data == cardData }

    override suspend fun updateLoyaltyCard(card: LoyaltyCard) = loyaltyCardsDao.insert(card.map())

    override suspend fun removeLoyaltyCard(card: LoyaltyCard) = loyaltyCardsDao.delete(card.map())

    private fun DBLoyaltyCard.map(): LoyaltyCard = LoyaltyCard(name, data, codeType, cardColor)
    private fun LoyaltyCard.map(): DBLoyaltyCard = DBLoyaltyCard(name, data, codeType, cardColor)
}

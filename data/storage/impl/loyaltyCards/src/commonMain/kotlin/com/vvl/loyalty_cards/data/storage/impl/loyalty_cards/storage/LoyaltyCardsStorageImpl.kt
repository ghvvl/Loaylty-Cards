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

    override suspend fun updateLoyaltyCard(card: LoyaltyCard) {
        val oldCard = getLoyaltyCard(card.data)
        val newData = card.copy(
            name = card.name.ifEmpty { oldCard?.name.orEmpty() }
        )
        loyaltyCardsDao.insert(newData.map())
    }

    override suspend fun removeLoyaltyCard(card: LoyaltyCard) =
        loyaltyCardsDao.deleteByData(card.data)

    override suspend fun replaceLoyaltyCards(cards: List<LoyaltyCard>) {
        // getAllAsFlow function are desc sorted so we need reverse here too
        loyaltyCardsDao.replaceData(cards.asReversed().map { it.map() })
    }

    private fun DBLoyaltyCard.map(): LoyaltyCard = LoyaltyCard(
        name = name,
        data = data,
        codeType = codeType,
        cardColor = cardColor
    )

    private fun LoyaltyCard.map(): DBLoyaltyCard = DBLoyaltyCard(
        id = 0,
        name = name,
        data = data,
        codeType = codeType,
        cardColor = cardColor
    )
}

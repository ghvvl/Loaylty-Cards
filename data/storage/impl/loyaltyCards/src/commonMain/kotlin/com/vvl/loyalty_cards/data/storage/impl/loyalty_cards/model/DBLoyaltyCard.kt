package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType

@Entity
internal data class DBLoyaltyCard(
    val name: String,
    @PrimaryKey
    val data: String,
    val codeType: LoyaltyCardCodeType,
    val cardColor: Int
)

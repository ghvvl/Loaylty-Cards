package com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType

@Entity(indices = [Index(value = ["data"], unique = true)])
internal data class DBLoyaltyCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val data: String,
    val codeType: LoyaltyCardCodeType,
    val cardColor: Int
)

package com.vvl.loyalty_cards.data.storage.impl.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vvl.loyalty_cards.common.model.WidgetId

@Entity
data class DBWidgetState(
    @PrimaryKey
    val widgetId: WidgetId,
    val widgetCardsIds: List<String>
)

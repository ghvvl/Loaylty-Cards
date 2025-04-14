package com.vvl.loyalty_cards.common.model

import kotlinx.serialization.Serializable

@Serializable
data class WidgetState(
    val widgetId: WidgetId,
    // IOS can have only one widgetCard
    val widgetCards: Set<LoyaltyCard>
)

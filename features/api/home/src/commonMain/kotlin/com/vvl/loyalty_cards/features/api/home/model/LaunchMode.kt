package com.vvl.loyalty_cards.features.api.home.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface LaunchMode {
    @Serializable
    object LoyaltyCardsList : LaunchMode

    @Serializable
    object WidgetList : LaunchMode
}

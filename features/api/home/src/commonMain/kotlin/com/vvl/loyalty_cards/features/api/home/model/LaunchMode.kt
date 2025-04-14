package com.vvl.loyalty_cards.features.api.home.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface LaunchMode {
    @Serializable
    class LoyaltyCardsList : LaunchMode

    @Serializable
    class WidgetList : LaunchMode
}

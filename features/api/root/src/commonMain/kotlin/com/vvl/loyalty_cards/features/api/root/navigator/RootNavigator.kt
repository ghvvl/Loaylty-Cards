package com.vvl.loyalty_cards.features.api.root.navigator

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.WidgetId

interface RootNavigator {
    fun openLoyaltyCardsList()

    fun openLoyaltyCardDetails(card: LoyaltyCard, replaceCurrent: Boolean = false)

    fun openAddLoyaltyCard()

    fun openWidgetDetails(widgetId: WidgetId)

    fun onBackClicked()
}

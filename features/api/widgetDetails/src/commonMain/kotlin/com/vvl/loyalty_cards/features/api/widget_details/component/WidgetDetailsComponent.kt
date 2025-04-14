package com.vvl.loyalty_cards.features.api.widget_details.component

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.features.api.widget_details.model.UIWidgetLoyaltyCards
import kotlinx.coroutines.flow.Flow

interface WidgetDetailsComponent {

    val isSingleSelection: Boolean

    val providedWidgetId: WidgetId

    val loyaltyCards: Flow<List<UIWidgetLoyaltyCards>>

    fun onLoyaltyCardClicked(card: LoyaltyCard)

    fun onBackClicked()
}

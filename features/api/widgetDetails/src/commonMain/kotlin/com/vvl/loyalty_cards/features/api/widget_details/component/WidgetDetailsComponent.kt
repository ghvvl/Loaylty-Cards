package com.vvl.loyalty_cards.features.api.widget_details.component

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.features.api.widget_details.model.UIWidgetLoyaltyCards
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface WidgetDetailsComponent {

    val isSingleSelection: Boolean

    val providedWidgetId: WidgetId

    val showResetButton: StateFlow<Boolean>

    val showRemoveAllButton: StateFlow<Boolean>

    val loyaltyCards: Flow<List<UIWidgetLoyaltyCards>>

    fun onRemoveAllClicked()

    fun onResetClicked()

    fun onLoyaltyCardClicked(card: LoyaltyCard)

    fun onBackClicked()
}

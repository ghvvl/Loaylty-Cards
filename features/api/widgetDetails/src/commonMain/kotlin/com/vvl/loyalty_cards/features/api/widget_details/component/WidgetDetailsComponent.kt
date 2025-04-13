package com.vvl.loyalty_cards.features.api.widget_details.component

import com.vvl.loyalty_cards.common.model.WidgetState
import kotlinx.coroutines.flow.Flow

interface WidgetDetailsComponent {

    val widgetState: Flow<WidgetState?>

    fun onBackClicked()
}

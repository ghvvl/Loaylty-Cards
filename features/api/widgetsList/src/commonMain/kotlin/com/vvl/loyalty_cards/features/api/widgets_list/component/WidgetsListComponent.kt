package com.vvl.loyalty_cards.features.api.widgets_list.component

import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.common.model.WidgetState
import kotlinx.coroutines.flow.Flow

interface WidgetsListComponent {

    val widgets: Flow<List<WidgetState>>

    fun onWidgetClicked(widgetId: WidgetId)
}

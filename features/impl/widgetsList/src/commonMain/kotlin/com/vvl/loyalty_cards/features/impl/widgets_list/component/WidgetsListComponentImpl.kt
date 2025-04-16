package com.vvl.loyalty_cards.features.impl.widgets_list.component

import com.arkivanov.decompose.ComponentContext
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.common.model.WidgetState
import com.vvl.loyalty_cards.data.storage.api.widget.storage.WidgetStorage
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.api.widgets_list.component.WidgetsListComponent
import kotlinx.coroutines.flow.Flow

internal class WidgetsListComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    widgetStorage: WidgetStorage
) : WidgetsListComponent, ComponentContext by componentContext {

    override val widgets: Flow<List<WidgetState>> = widgetStorage.widgetStates

    override fun onWidgetClicked(widgetId: WidgetId) {
        rootNavigator.openWidgetDetails(widgetId)
    }
}

package com.vvl.loyalty_cards.features.impl.widgets_list.component

import com.arkivanov.decompose.ComponentContext
import com.vvl.loyalty_cards.common.model.WidgetState
import com.vvl.loyalty_cards.data.storage.api.widget.storage.WidgetStorage
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import com.vvl.loyalty_cards.features.api.widgets_list.component.WidgetsListComponent
import kotlinx.coroutines.flow.Flow

internal class WidgetsListComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    private val widgetStorage: WidgetStorage,
    private val widgetDelegate: WidgetDelegate
) : WidgetsListComponent, ComponentContext by componentContext {

    override val widgets: Flow<List<WidgetState>> = widgetStorage.widgetStates
}
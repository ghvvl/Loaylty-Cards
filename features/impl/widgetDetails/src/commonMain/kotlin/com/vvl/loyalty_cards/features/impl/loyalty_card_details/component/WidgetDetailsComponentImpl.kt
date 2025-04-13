package com.vvl.loyalty_cards.features.impl.loyalty_card_details.component

import com.arkivanov.decompose.ComponentContext
import com.vvl.loyalty_cards.common.model.WidgetId
import com.vvl.loyalty_cards.common.model.WidgetState
import com.vvl.loyalty_cards.data.storage.api.widget.storage.WidgetStorage
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import com.vvl.loyalty_cards.features.api.widget_details.component.WidgetDetailsComponent
import kotlinx.coroutines.flow.Flow

internal class WidgetDetailsComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    override val providedWidgetId: WidgetId,
    private val widgetStorage: WidgetStorage,
    private val widgetDelegate: WidgetDelegate
) : WidgetDetailsComponent, ComponentContext by componentContext {

    override val widgetState: Flow<WidgetState?> = widgetStorage.getWidgetStateFlow(providedWidgetId)

    override fun onBackClicked() = rootNavigator.onBackClicked()
}
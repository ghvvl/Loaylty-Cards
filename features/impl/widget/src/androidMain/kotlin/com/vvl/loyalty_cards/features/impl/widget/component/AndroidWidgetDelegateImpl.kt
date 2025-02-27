package com.vvl.loyalty_cards.features.impl.widget.component

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import com.vvl.loyalty_cards.features.impl.widget.widget.Widget

internal class AndroidWidgetDelegateImpl(
    private val context: Context
) : WidgetDelegate {

    override suspend fun updateAllWidgets() {
        Widget().updateAll(context)
    }
}
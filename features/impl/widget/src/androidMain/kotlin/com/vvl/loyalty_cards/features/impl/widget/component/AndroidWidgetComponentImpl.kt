package com.vvl.loyalty_cards.features.impl.widget.component

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.vvl.loyalty_cards.features.api.widget.component.WidgetComponent
import com.vvl.loyalty_cards.features.impl.widget.widget.Widget
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal class AndroidWidgetComponentImpl(
    private val context: Context,
    private val scope: CoroutineScope
) : WidgetComponent {

    override fun updateAllWidgets() {
        scope.launch { Widget().updateAll(context) }
    }
}
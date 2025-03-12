package com.vvl.loyalty_cards.features.impl.widget.receiver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.vvl.loyalty_cards.features.impl.widget.widget.Widget

internal class WidgetReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = Widget()
}

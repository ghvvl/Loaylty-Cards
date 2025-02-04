package com.vvl.loyalty_cards.features.impl.widget.receiver

import android.content.Context
import android.content.Intent
import android.os.IBinder
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.vvl.loyalty_cards.features.impl.widget.widget.LoyaltyCardsWidget

internal class LoyaltyCardsReceiver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = LoyaltyCardsWidget()

    override fun peekService(myContext: Context?, service: Intent?): IBinder {
        return super.peekService(myContext, service)
    }
}
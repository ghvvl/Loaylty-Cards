package com.vvl.loyalty_cards.features.impl.loyalty_card_details.di

import com.vvl.loyalty_cards.features.api.widget_details.component.WidgetDetailsComponent
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.component.WidgetDetailsComponentImpl
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.utils.isSingleSelection
import org.koin.dsl.module

val widgetDetailsModule = module {
    factory<WidgetDetailsComponent> {
        WidgetDetailsComponentImpl(
            componentContext = it.get(),
            rootNavigator = it.get(),
            isSingleSelection = isSingleSelection(),
            providedWidgetId = it.get(),
            widgetStorage = get(),
            loyaltyCardsStorage = get(),
            widgetDelegate = get(),
        )
    }
}

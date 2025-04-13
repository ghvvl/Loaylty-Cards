package com.vvl.loyalty_cards.features.impl.loyalty_card_details.di

import com.vvl.loyalty_cards.features.api.widget_details.component.WidgetDetailsComponent
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.component.WidgetDetailsComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val widgetDetailsModule = module {
    factoryOf(::WidgetDetailsComponentImpl) bind WidgetDetailsComponent::class
}

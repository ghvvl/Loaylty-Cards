package com.vvl.loyalty_cards.features.impl.widget.di

import com.vvl.loyalty_cards.features.api.widget.component.WidgetComponent
import com.vvl.loyalty_cards.features.impl.widget.component.AndroidWidgetComponentImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val androidWidgetModule = module {
    singleOf(::AndroidWidgetComponentImpl) bind WidgetComponent::class
}
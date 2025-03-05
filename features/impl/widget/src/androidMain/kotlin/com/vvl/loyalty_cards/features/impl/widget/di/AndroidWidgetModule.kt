package com.vvl.loyalty_cards.features.impl.widget.di

import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import com.vvl.loyalty_cards.features.impl.widget.component.AndroidWidgetDelegateImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val androidWidgetModule = module {
    singleOf(::AndroidWidgetDelegateImpl) bind WidgetDelegate::class
}
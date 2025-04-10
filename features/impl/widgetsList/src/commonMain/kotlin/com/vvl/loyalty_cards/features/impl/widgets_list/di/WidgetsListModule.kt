package com.vvl.loyalty_cards.features.impl.widgets_list.di

import com.vvl.loyalty_cards.features.api.widgets_list.component.WidgetsListComponent
import com.vvl.loyalty_cards.features.impl.widgets_list.component.WidgetsListComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val widgetsListModule = module {
    factoryOf(::WidgetsListComponentImpl) bind WidgetsListComponent::class
}
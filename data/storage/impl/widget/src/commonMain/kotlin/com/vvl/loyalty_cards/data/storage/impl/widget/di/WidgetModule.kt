package com.vvl.loyalty_cards.data.storage.impl.widget.di

import com.vvl.loyalty_cards.data.storage.api.widget.storage.WidgetStorage
import com.vvl.loyalty_cards.data.storage.impl.widget.storage.WidgetStorageImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val widgetModule = module {
    singleOf(::WidgetStorageImpl) bind WidgetStorage::class
}

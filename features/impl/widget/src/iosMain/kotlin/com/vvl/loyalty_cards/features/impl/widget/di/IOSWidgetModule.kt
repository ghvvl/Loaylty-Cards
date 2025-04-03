package com.vvl.loyalty_cards.features.impl.widget.di

import com.vvl.loyalty_cards.features.api.widget.component.WidgetDelegate
import com.vvl.loyalty_cards.features.impl.widget.delegate.IOSWidgetDelegateImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.binds
import org.koin.dsl.module

val iosWidgetModule = module {
    singleOf(::IOSWidgetDelegateImpl) binds arrayOf(
        WidgetDelegate::class,
        IOSWidgetDelegateImpl::class
    )
}

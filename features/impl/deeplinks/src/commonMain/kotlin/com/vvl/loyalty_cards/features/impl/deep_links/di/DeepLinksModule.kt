package com.vvl.loyalty_cards.features.impl.deep_links.di

import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksHandler
import com.vvl.loyalty_cards.features.api.deep_links.DeepLinksProvider
import com.vvl.loyalty_cards.features.impl.deep_links.manager.DeepLinksManager
import org.koin.dsl.binds
import org.koin.dsl.module

val deepLinksModule = module {
    single { DeepLinksManager(get()) } binds arrayOf(
        DeepLinksProvider::class,
        DeepLinksHandler::class
    )
}
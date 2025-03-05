package com.vvl.loyalty_cards.features.impl.loyalty_cards_list.di

import com.vvl.loyalty_cards.features.impl.loyalty_cards_list.component.LoyaltyCardsListComponentImpl
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loyaltyCardsListModule = module {
    factoryOf(::LoyaltyCardsListComponentImpl) bind LoyaltyCardsListComponent::class
}

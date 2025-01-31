package com.vvl.loyalty_cards.impl.add_loyalty_card.di

import com.vvl.loyalty_cards.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.impl.add_loyalty_card.component.AddLoyaltyCardComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val addLoyaltyCardModule = module {
    factoryOf(::AddLoyaltyCardComponentImpl) bind AddLoyaltyCardComponent::class
}
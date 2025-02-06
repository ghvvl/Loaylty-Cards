package com.vvl.loyalty_cards.features.impl.add_loyalty_card.di

import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.component.AddLoyaltyCardComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val addLoyaltyCardModule = module {
    factoryOf(::AddLoyaltyCardComponentImpl) bind AddLoyaltyCardComponent::class
}

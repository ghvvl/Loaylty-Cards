package com.vvl.loyalty_cards.features.impl.loyalty_card_details.di

import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.component.LoyaltyCardDetailsComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loyaltyCardDetailsModule = module {
    factoryOf(::LoyaltyCardDetailsComponentImpl) bind LoyaltyCardDetailsComponent::class
}

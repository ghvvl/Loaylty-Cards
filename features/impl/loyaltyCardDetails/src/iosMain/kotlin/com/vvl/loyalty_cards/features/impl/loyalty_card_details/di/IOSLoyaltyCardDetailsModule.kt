package com.vvl.loyalty_cards.features.impl.loyalty_card_details.di

import com.vvl.loyalty_cards.features.impl.loyalty_card_details.delegate.BrightnessDelegate
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.delegate.IOSBrightnessDelegate
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val iosLoyaltyCardDetailsModule = module {
    includes(loyaltyCardDetailsModule)
    singleOf(::IOSBrightnessDelegate) bind BrightnessDelegate::class
}

package com.vvl.loyalty_cards.features.impl.loyalty_card_details.di

import android.app.Activity
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.delegate.AndroidBrightnessDelegate
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.delegate.BrightnessDelegate
import org.koin.dsl.bind
import org.koin.dsl.module

fun androidLoyaltyCardDetailsModule(activity: Activity) = module {
    includes(loyaltyCardDetailsModule)
    single { AndroidBrightnessDelegate(activity) } bind BrightnessDelegate::class
}

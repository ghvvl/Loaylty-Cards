package com.vvl.loyalty_cards.features.impl.add_loyalty_card.di

import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.component.AddLoyaltyCardComponentImpl
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.component.AndroidAddLoyaltyCardComponentImpl
import org.koin.dsl.module

val androidAddLoyaltyCardModule = module {
    factory<AddLoyaltyCardComponent> {
        AndroidAddLoyaltyCardComponentImpl(
            get(),
            AddLoyaltyCardComponentImpl(it.get(), it.get(), get())
        )
    }
}

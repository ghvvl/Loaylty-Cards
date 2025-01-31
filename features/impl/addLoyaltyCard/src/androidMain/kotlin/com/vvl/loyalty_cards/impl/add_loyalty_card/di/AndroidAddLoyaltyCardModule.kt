package com.vvl.loyalty_cards.impl.add_loyalty_card.di

import com.vvl.loyalty_cards.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.impl.add_loyalty_card.component.AddLoyaltyCardComponentImpl
import com.vvl.loyalty_cards.impl.add_loyalty_card.component.AndroidAddLoyaltyCardComponentImpl
import org.koin.dsl.module

val androidAddLoyaltyCardModule = module {
    includes(addLoyaltyCardModule)
    factory<AddLoyaltyCardComponent> {
        AndroidAddLoyaltyCardComponentImpl(
            get(),
            AddLoyaltyCardComponentImpl(it.get(), it.get(), get())
        )
    }
}
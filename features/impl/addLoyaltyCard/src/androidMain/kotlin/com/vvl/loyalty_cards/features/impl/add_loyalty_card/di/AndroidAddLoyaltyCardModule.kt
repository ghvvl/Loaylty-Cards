package com.vvl.loyalty_cards.features.impl.add_loyalty_card.di

import com.vvl.loyalty_cards.features.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.component.AddLoyaltyCardComponentImpl
import com.vvl.loyalty_cards.features.impl.add_loyalty_card.component.AndroidAddLoyaltyCardComponentImpl
import org.koin.dsl.module

val androidAddLoyaltyCardModule = module {
    factory<AddLoyaltyCardComponent> {
        AndroidAddLoyaltyCardComponentImpl(
            context = get(),
            delegatedComponent = AddLoyaltyCardComponentImpl(
                componentContext = it.get(),
                rootNavigator = it.get(),
                loyaltyCardsStorage = get(),
                widgetDelegate = get()
            )
        )
    }
}

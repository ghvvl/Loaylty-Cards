package com.vvl.loyalty_cards.app.di

import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.impl.loyalty_card_details.component.LoyaltyCardDetailsComponentImpl
import com.vvl.loyalty_cards.data.storage.impl.loyalty_cards.di.loyaltyCardsModule
import com.vvl.loyalty_cards.features.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.features.api.root.component.RootComponent
import com.com.vvl.loyalty_cards.features.impl.loyalty_cards_list.component.LoyaltyCardsListComponentImpl
import com.com.vvl.loyalty_cards.features.impl.root.component.RootComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val appModule = module {
    includes(loyaltyCardsModule)
    factory {
        RootComponentImpl(
            it.get(),
            { context, holder -> get { parametersOf(context, holder) } },
            { context, holder -> get { parametersOf(context, holder) } },
            { context, holder -> get { parametersOf(context, holder) } },
        )
    } bind RootComponent::class
    factoryOf(::LoyaltyCardsListComponentImpl) bind LoyaltyCardsListComponent::class
    factoryOf(::LoyaltyCardDetailsComponentImpl) bind LoyaltyCardDetailsComponent::class
}
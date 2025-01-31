package com.vvl.loyalty_cards

import com.vvl.loyalty_cards.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.api.loyalty_cards_list.component.LoyaltyCardsListComponent
import com.vvl.loyalty_cards.api.root.component.RootComponent
import com.vvl.loyalty_cards.impl.loyalty_card_details.component.LoyaltyCardDetailsComponentImpl
import com.vvl.loyalty_cards.impl.loyalty_cards.storage.LoyaltyCardsStorageImpl
import com.vvl.loyalty_cards.impl.loyalty_cards_list.component.LoyaltyCardsListComponentImpl
import com.vvl.loyalty_cards.impl.root.component.RootComponentImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val appModule = module {
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
    singleOf(::LoyaltyCardsStorageImpl) bind LoyaltyCardsStorage::class
}
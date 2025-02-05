package com.vvl.loyalty_cards.features.impl.loyalty_card_details.component

import com.arkivanov.decompose.ComponentContext
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.features.api.loyalty_card_details.component.LoyaltyCardDetailsComponent
import com.vvl.loyalty_cards.features.api.root.navigator.RootNavigator

internal class LoyaltyCardDetailsComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    override val loyaltyCard: LoyaltyCard
) : LoyaltyCardDetailsComponent, ComponentContext by componentContext {

    override fun onBackClicked() = rootNavigator.onBackClicked()
}
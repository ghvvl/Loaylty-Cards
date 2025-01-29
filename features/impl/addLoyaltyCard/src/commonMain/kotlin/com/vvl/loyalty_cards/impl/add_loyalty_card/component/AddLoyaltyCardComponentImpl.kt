package com.vvl.loyalty_cards.impl.add_loyalty_card.component

import com.arkivanov.decompose.ComponentContext
import com.vvl.loyalty_cards.api.add_loyalty_card.component.AddLoyaltyCardComponent
import com.vvl.loyalty_cards.api.root.navigator.RootNavigator
import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.vvl.loyalty_cards.api.loyalty_cards.storage.LoyaltyCardsStorage
import kotlinx.coroutines.launch

class AddLoyaltyCardComponentImpl(
    componentContext: ComponentContext,
    private val rootNavigator: RootNavigator,
    private val loyaltyCardsStorage: LoyaltyCardsStorage
) : AddLoyaltyCardComponent, ComponentContext by componentContext {

    override fun onCodeReceived(code: String, codeType: LoyaltyCardCodeType) {
        val loyaltyCard = LoyaltyCard(code, codeType)
        coroutineScope().launch {
            loyaltyCardsStorage.addLoyaltyCard(loyaltyCard)
            rootNavigator.openLoyaltyCardDetails(loyaltyCard, true)
        }
    }

    override fun onSettingsClicked() {
        //   launcher.openSettingsScreen()
    }

    override fun onBackClicked() {
        rootNavigator.onBackClicked()
    }
}
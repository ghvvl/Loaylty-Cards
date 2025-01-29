package com.vvl.loyalty_cards.api.add_loyalty_card.component

import com.vvl.loyalty_cards.common.model.LoyaltyCardCodeType

interface AddLoyaltyCardComponent {

    fun onCodeReceived(code: String, codeType: LoyaltyCardCodeType)

    fun onSettingsClicked()

    fun onBackClicked()
}
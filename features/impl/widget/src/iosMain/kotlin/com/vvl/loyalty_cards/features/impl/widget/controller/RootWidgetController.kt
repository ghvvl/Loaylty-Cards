package com.vvl.loyalty_cards.features.impl.widget.controller

import com.vvl.loyalty_cards.common.model.LoyaltyCard
import com.vvl.loyalty_cards.data.storage.api.loyalty_cards.storage.LoyaltyCardsStorage
import com.vvl.loyalty_cards.features.impl.widget.delegate.IOSWidgetDelegateImpl
import kotlinx.coroutines.flow.first
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RootWidgetController() : KoinComponent {

    private val storage: LoyaltyCardsStorage by inject()
    private val widgetDelegate: IOSWidgetDelegateImpl by inject()

    suspend fun current(): List<LoyaltyCard> = storage.loyaltyCards.first()

    fun setCallback(callback: () -> Unit) {
        widgetDelegate.callback = callback
    }
}
